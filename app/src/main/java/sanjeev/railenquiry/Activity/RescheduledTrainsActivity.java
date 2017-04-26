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
import sanjeev.railenquiry.Adapter.RescheduledTrainsAdapter;
import sanjeev.railenquiry.Model.CancelledTrains;
import sanjeev.railenquiry.Model.RescheduledTrains;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/26/2017.
 */

public class RescheduledTrainsActivity extends AppCompatActivity {

    RecyclerView rescheduledRecyclerViews;
    ProgressBar pro;
    List<RescheduledTrains> data;
    String date;
    private String TAG = RescheduledTrainsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelled_recycler);
        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        initViews();
        new GetCancelledTrains().execute();

    }

    private void initViews() {
        rescheduledRecyclerViews = (RecyclerView) findViewById(R.id.cancelled_recyclerview);
        pro = (ProgressBar) findViewById(R.id.pro);
        rescheduledRecyclerViews.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };

        rescheduledRecyclerViews.setLayoutManager(layoutManager);
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
            String url = "http://api.railwayapi.com/rescheduled/date/" + date + "/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray rescheduledArray = jsonObject.getJSONArray("trains");

                    for (int i = 0; i < rescheduledArray.length(); i++) {

                        RescheduledTrains res = new RescheduledTrains();
                        JSONObject rescheduledObject = rescheduledArray.getJSONObject(i);
                        JSONObject fromObject = rescheduledObject.getJSONObject("from");
                        JSONObject toObject = rescheduledObject.getJSONObject("to");


                        String train_number = rescheduledObject.getString("number");
                        String train_name = rescheduledObject.getString("name");
                        String rescheduled_date = rescheduledObject.getString("rescheduled_date");
                        String rescheduled_time = rescheduledObject.getString("rescheduled_time");
                        String time_diff = rescheduledObject.getString("time_diff");

                        String to_code = toObject.getString("code");
                        String from_code = fromObject.getString("code");


                        res.setTrain_name(train_name);
                        res.setTrain_number(train_number);
                        res.setRescheduled_date(rescheduled_date);
                        res.setRescheduled_time(rescheduled_time);
                        res.setTime_diff(time_diff);
                        res.setTo_code(to_code);
                        res.setFrom_code(from_code);

                        data.add(res);
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

            RescheduledTrainsAdapter adapter = new RescheduledTrainsAdapter(getApplicationContext(), data);
            rescheduledRecyclerViews.setAdapter(adapter);
        }
    }


}
