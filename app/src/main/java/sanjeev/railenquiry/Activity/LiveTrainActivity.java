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

import sanjeev.railenquiry.Adapter.CancelledTrainAdapter;
import sanjeev.railenquiry.Adapter.LiveTrainAdapter;
import sanjeev.railenquiry.Model.CancelledTrains;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class LiveTrainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar pro;
    List<LiveTrain> data;
    private String TAG = LiveTrainActivity.class.getSimpleName();
    LiveTrain live;
    String trainno;
    TextView train_number, train_status, station_code, actual_arrival, actual_dept, status;
    TextView sch_arrival;
    TextView sch_dept;
    TextView station_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_train_recycler);
        train_number = (TextView) findViewById(R.id.train_number);
        train_status = (TextView) findViewById(R.id.train_status);
        station_code = (TextView) findViewById(R.id.station_code);
        station_name = (TextView) findViewById(R.id.station_name);
        actual_arrival = (TextView) findViewById(R.id.actual_arrival);
        actual_dept = (TextView) findViewById(R.id.actual_dept);
        status = (TextView) findViewById(R.id.status);
        sch_arrival = (TextView) findViewById(R.id.sch_arrival);
        sch_dept = (TextView) findViewById(R.id.sch_dept);
        trainno = getIntent().getExtras().getString("train_no");
        // dataurl = getIntent().getExtras().getString("train_no");
        recyclerView = (RecyclerView) findViewById(R.id.livetrain_recyclerview);
        recyclerView.setHasFixedSize(true);
     //   pro.setVisibility(View.GONE);
//        socre_recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        new GetLiveTrainStatus().execute();
    }

    private class GetLiveTrainStatus extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   pro.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();

            String url = "http://api.railwayapi.com/live/train/"+trainno+"/doj/20170428/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    live = new LiveTrain();
                    String train_no = jsonObject.getString("train_number");
                    String position = jsonObject.getString("position");

                    JSONObject current_StationObject = jsonObject.getJSONObject("current_station");

                    JSONObject stationObject = current_StationObject.getJSONObject("station_");

                    String station_code = stationObject.getString("code");
                    String station_name = stationObject.getString("name");

                    String act_dept = current_StationObject.getString("actdep");
                    String act_arrival = current_StationObject.getString("actarr");
                    String sch_dept = current_StationObject.getString("schdep");
                    String sch_arrival = current_StationObject.getString("scharr");
                    String status = current_StationObject.getString("status");


                    JSONArray routeArray = jsonObject.getJSONArray("route");

                    for (int i = 0; i < routeArray.length(); i++) {

                        LiveTrain live = new LiveTrain();

                        JSONObject routeObject = routeArray.getJSONObject(i);
                        JSONObject stationrouteObject = routeObject.getJSONObject("station_");

                        String rstation_code = stationrouteObject.getString("code");
                        String rstation_name = stationrouteObject.getString("name");

                        String ract_dept = routeObject.getString("actdep");
                        String ract_arrival = routeObject.getString("actarr");
                        String rsch_dept = routeObject.getString("schdep");
                        String rsch_arrival = routeObject.getString("scharr");
                        String rstatus = routeObject.getString("status");


                        live.setRstation_code(rstation_code);
                        live.setRstation_name(rstation_name);
                        live.setRact_dept(ract_dept);
                        live.setRact_arrival(ract_arrival);
                        live.setRsch_dept(rsch_dept);
                        live.setRsch_arrival(rsch_arrival);
                        live.setRstatus(rstatus);


                        data.add(live);


                    }

                    live.setAct_arrival(act_arrival);
                    live.setAct_dept(act_dept);
                    live.setTrain_no(train_no);
                    live.setPosition(position);
                    live.setSch_arrival(sch_arrival);
                    live.setSch_dept(sch_dept);
                    live.setStatus(status);
                    live.setStation_code(station_code);
                    live.setStation_name(station_name);

                    data.add(live);

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
         //   pro.setVisibility(View.GONE);

            train_number.setText(live.getTrain_no());
            train_status.setText(live.getPosition());
            station_code.setText(live.getStation_code());
            station_name.setText(live.getStation_name());
            actual_arrival.setText(live.getAct_arrival());
            actual_dept.setText(live.getAct_dept());
            status.setText(live.getStatus());
            sch_dept.setText(live.getSch_dept());
            sch_arrival.setText(live.getSch_arrival());

            LiveTrainAdapter adapter = new LiveTrainAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }
}
