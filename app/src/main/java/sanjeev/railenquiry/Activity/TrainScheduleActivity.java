package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import sanjeev.railenquiry.Adapter.ScheduleAdapter;
import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

public class TrainScheduleActivity extends AppCompatActivity {
    EditText edt_train_no;
    Button search;
    String Train_No;
    private String webResponse = XmlPullParser.NO_NAMESPACE;
    RecyclerView recyclerView;
    List<Schedule> data = new ArrayList<>();
    private String TAG = TrainScheduleActivity.class.getSimpleName();
    private TextView txt_train_no;
    private TextView txt_train_name;
    private TextView train_runson;
    Schedule sch;
    LinearLayout lltrain_no;
    CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_schedule_recycler);

        edt_train_no = (EditText) findViewById(R.id.edt_train_no);
        search = (Button) findViewById(R.id.btn_search);
        lltrain_no = (LinearLayout) findViewById(R.id.lltrainno);
        card = (CardView) findViewById(R.id.card_view);
        txt_train_no = (TextView) findViewById(R.id.txt_train_no);
        txt_train_name = (TextView) findViewById(R.id.txt_train_name);
        // train_runson = (TextView) findViewById(R.id.train_runson);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_schedule);
        recyclerView.setHasFixedSize(true);
//        socre_recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        lltrain_no.setVisibility(View.GONE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Train_No = edt_train_no.getText().toString();

                GetTrainSchedule schedule_train = new GetTrainSchedule();
                schedule_train.execute();
            }
        });
    }


    private class GetTrainSchedule extends AsyncTask<String, Void, String> {

        String url = "http://api.railwayapi.com/route/train/" + Train_No + "/apikey/nn4ypiuv/";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();


            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //    Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject jsonTrain = jsonObject.getJSONObject("train");
                    JSONArray jsonArrayDays = jsonTrain.getJSONArray("days");

                    JSONArray jsonArrayRoute = jsonObject.getJSONArray("route");

                    // Getting JSON Array node
                    //      JSONArray contacts = jsonObj.getJSONArray("stories");

                    // looping through All Contacts

                    String train_no = jsonTrain.getString("number");
                    String train_name = jsonTrain.getString("name");


                    sch = new Schedule();
                    sch.setTrain_name(train_name);
                    sch.setTrain_no(train_no);



                   /* for (int i = 0; i < jsonArrayDays.length(); i++) {

                        JSONObject c = jsonArrayDays.getJSONObject(i);

                        Schedule sch1 = new Schedule();

                        String days_code = c.getString("day-code");

                        sch1.setDays_code(days_code);

                        data.add(sch1);

                    }*/

                    for (int j = 0; j < jsonArrayRoute.length(); j++) {
                        JSONObject r = jsonArrayRoute.getJSONObject(j);

                        Schedule sch2 = new Schedule();

                        String station_name = r.getString("code");
                        String arrival = r.getString("scharr");
                        String departure = r.getString("schdep");
                        String distance = r.getString("distance");
                        String days = r.getString("day");
                        String halt = r.getString("halt");

                        sch2.setStation_name(station_name);
                        sch2.setArrival(arrival);
                        sch2.setDeparture(departure);
                        sch2.setDistance(distance);
                        sch2.setDays(days);
                        sch2.setHalt(halt);

                        data.add(sch2);
                    }

                    data.add(sch);

                } catch (final JSONException e) {
                    e.printStackTrace();

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");


            }
//            webResponse = contactList.toString();
            return webResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            // pro.setVisibility(View.GONE);
            /**
             * Updating parsed JSON data into ListView
             * */

//            String str= contact.get("hline");
//            int i = contact.size();

            /*ArrayList<DD> androidVersions = prepareData();*/
//            card.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            lltrain_no.setVisibility(View.VISIBLE);


            txt_train_name.setText(sch.getTrain_name());
            txt_train_no.setText(sch.getTrain_no());
            //       train_runson.setText(sch.getDays_code());

            ScheduleAdapter adapter = new ScheduleAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);


        }

    }


}
