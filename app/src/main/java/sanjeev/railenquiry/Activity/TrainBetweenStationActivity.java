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
import sanjeev.railenquiry.Adapter.TrainBetweenStationAdapter;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.Model.TrainBetweenStation;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class TrainBetweenStationActivity extends AppCompatActivity {


    String t_source;
    String t_destination;

    TrainBetweenStation tbs;
    List<TrainBetweenStation> data;
    private String TAG = TrainBetweenStationActivity.class.getSimpleName();
    ProgressBar pro;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_between_station_recycler);

        pro = (ProgressBar) findViewById(R.id.pro);
        t_source = getIntent().getExtras().getString("t_source");
        t_destination = getIntent().getExtras().getString("t_dest");


        recyclerView = (RecyclerView) findViewById(R.id.trainbetweenstation_recyclerview);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        new GetTrainBetweenStationDetails().execute();
    }

    private class GetTrainBetweenStationDetails extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pro.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();

            String url = "http://api.railwayapi.com/between/source/" + t_source + "/dest/" + t_destination + "/date/11-05/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray trainArray = jsonObject.getJSONArray("train");
                            for(int i = 0;i<trainArray.length();i++)
                            {
                                JSONObject trainObject = trainArray.getJSONObject(i);

                                JSONObject jsonfrom = trainObject.getJSONObject("from");
                                JSONObject jsonto = trainObject.getJSONObject("to");
                                tbs = new TrainBetweenStation();
                                String from = jsonfrom.getString("name");
                                String to = jsonto.getString("name");


                                String train_no = trainObject.getString("number");
                                String train_name = trainObject.getString("name");

                                String arrival = trainObject.getString("dest_arrival_time");
                                String departure = trainObject.getString("src_departure_time");
                                String travel_time = trainObject.getString("travel_time");

                                tbs.setTrain_from(from);
                                tbs.setTrain_to(to);
                                tbs.setTrain_no(train_no);
                                tbs.setTrain_name(train_name);
                                tbs.setArrival(arrival);
                                tbs.setDeparture(departure);
                                tbs.setTravel_distance(travel_time);


                                data.add(tbs);
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


            TrainBetweenStationAdapter adapter = new TrainBetweenStationAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }
}
