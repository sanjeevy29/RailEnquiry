package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sanjeev.railenquiry.Adapter.CancelledTrainAdapter;
import sanjeev.railenquiry.Model.CancelledTrains;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/26/2017.
 */

public class CancelledTrainActivity extends AppCompatActivity {

    RecyclerView cancelledRecyclerViews;
    ProgressBar pro;
    List<CancelledTrains> data;
    String date;
    private String TAG = CancelledTrainActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelled_recycler);
        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        initViews();
        new GetCancelledTrains().execute();

    }

    private void initViews() {
        cancelledRecyclerViews = (RecyclerView) findViewById(R.id.cancelled_recyclerview);
        pro = (ProgressBar) findViewById(R.id.pro);
        cancelledRecyclerViews.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };

        cancelledRecyclerViews.setLayoutManager(layoutManager);
    }


    private class GetCancelledTrains extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pro.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();
            String url = "http://api.railwayapi.com/cancelled/date/" + date + "/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray cancelledArray = jsonObject.getJSONArray("trains");

                    for (int i = 0; i < cancelledArray.length(); i++) {

                        CancelledTrains can = new CancelledTrains();
                        JSONObject cancelledObject = cancelledArray.getJSONObject(i);

                        JSONObject sourceObject = cancelledObject.getJSONObject("source");
                        JSONObject destObject = cancelledObject.getJSONObject("dest");
                        JSONObject trainObject = cancelledObject.getJSONObject("train");


                        String source_code = sourceObject.getString("code");
                        String dest_code = destObject.getString("code");
                        String train_number = trainObject.getString("number");
                        String train_name = trainObject.getString("name");

                        can.setTrain_name(train_name);
                        can.setTrain_number(train_number);
                        can.setTrain_source(source_code);
                        can.setTrain_dest(dest_code);

                        data.add(can);
                    }

                } catch (final JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pro.setVisibility(View.GONE);

            CancelledTrainAdapter adapter = new CancelledTrainAdapter(getApplicationContext(), data);
            cancelledRecyclerViews.setAdapter(adapter);
        }
    }


}
