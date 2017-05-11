package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sanjeev.railenquiry.Adapter.FareAdapter;
import sanjeev.railenquiry.Adapter.LiveTrainAdapter;
import sanjeev.railenquiry.Adapter.StationStatusAdapter;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.Model.StationStatus;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class StationStatusActivity extends AppCompatActivity {

    String t_source;


    List<StationStatus> data;
    private String TAG = StationStatusActivity.class.getSimpleName();
    ProgressBar pro;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_status_recycler);

        t_source = getIntent().getExtras().getString("t_source");
        pro = (ProgressBar) findViewById(R.id.pro);
        recyclerView = (RecyclerView) findViewById(R.id.stationstatus_recyclerview);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        new GetStationDetails().execute();
    }

    private class GetStationDetails extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pro.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();

            String url = "http://api.railwayapi.com/arrivals/station/"+t_source+"/hours/2/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray trainArray = jsonObject.getJSONArray("train");

                    for (int i = 0; i < trainArray.length(); i++) {

                        StationStatus s = new StationStatus();

                        JSONObject trainObject = trainArray.getJSONObject(i);

                        String train_name = trainObject.getString("name");
                        String train_no = trainObject.getString("number");
                        String sch_dep = trainObject.getString("schdep");


                        s.setTrain_name(train_name);
                        s.setTrain_no(train_no);
                        s.setDep(sch_dep);

                        data.add(s);


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


            StationStatusAdapter adapter = new StationStatusAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }
}
