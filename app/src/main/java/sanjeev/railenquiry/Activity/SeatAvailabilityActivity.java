package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sanjeev.railenquiry.Adapter.FareAdapter;
import sanjeev.railenquiry.Adapter.LiveTrainAdapter;
import sanjeev.railenquiry.Adapter.SeatAvailabilityAdapter;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.Model.SeatAvailability;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class SeatAvailabilityActivity extends AppCompatActivity {


    String t_quota;
    String t_class;
    String t_trainno;
    String t_source;
    String t_destination;

    SeatAvailability seat;
    List<SeatAvailability> data;
    private String TAG = FareActivity.class.getSimpleName();
    TextView train_seat_name, train_no, train_name, train_from, train_to,class_code,class_name, quota_code, quota_name;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_recycler);

        t_class = getIntent().getExtras().getString("t_class");
        t_quota = getIntent().getExtras().getString("t_quota");
        t_trainno = getIntent().getExtras().getString("t_train_no");
        t_source = getIntent().getExtras().getString("t_source");
        t_destination = getIntent().getExtras().getString("t_dest");

        train_seat_name = (TextView) findViewById(R.id.train_seat_name);
        train_no = (TextView) findViewById(R.id.train_no);
        train_name = (TextView) findViewById(R.id.train_name);
        train_from = (TextView) findViewById(R.id.train_from);
        train_to = (TextView) findViewById(R.id.train_to);
        quota_code = (TextView) findViewById(R.id.quota_code);
        quota_name = (TextView) findViewById(R.id.quota_name);
        class_code = (TextView) findViewById(R.id.class_code);
        class_name = (TextView) findViewById(R.id.class_name);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_seatavailability);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        new GetFareDetails().execute();
    }

    private class GetFareDetails extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //   pro.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();

            String url = "http://api.railwayapi.com/check_seat/train/"+t_trainno+"/source/"+t_source+"/dest/"+t_destination+"/date/10-05-2017/class/SL/quota/"+t_quota+"/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject jsonfrom = jsonObject.getJSONObject("from");
                    JSONObject jsonto = jsonObject.getJSONObject("to");
                    JSONObject jsonclass = jsonObject.getJSONObject("class");
                    JSONObject jsonquota = jsonObject.getJSONObject("quota");
                    JSONArray seatArray = jsonObject.getJSONArray("availability");


                    seat = new SeatAvailability();

                    String train_name = jsonObject.getString("train_name");
                    String train_code = jsonObject.getString("train_number");

                    String from = jsonfrom.getString("name");
                    String to = jsonto.getString("name");

                    String class_name = jsonclass.getString("class_name");
                    String class_code = jsonclass.getString("class_code");

                    String quota_name = jsonquota.getString("quota_name");
                    String quota_code = jsonquota.getString("quota_code");


                    for (int i = 0; i < seatArray.length(); i++) {

                        SeatAvailability s = new SeatAvailability();

                        JSONObject seatObject = seatArray.getJSONObject(i);

                        String seat_status = seatObject.getString("status");
                        String seat_date = seatObject.getString("date");


                        s.setSeat_status(seat_status);
                        s.setSeat_date(seat_date);


                        data.add(s);


                    }

                    seat.setTo(to);
                    seat.setFrom(from);
                    seat.setTrain_name(train_name);
                    seat.setTrain_no(train_code);
                    seat.setQuota_code(quota_code);
                    seat.setQuota_name(quota_name);
                    seat.setClass_name(class_name);
                    seat.setClass_code(class_code);

                    data.add(seat);

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


            train_from.setText(seat.getFrom());
            train_to.setText(seat.getTo());
            train_name.setText(seat.getTrain_name());
            train_no.setText(seat.getTrain_no());
            quota_name.setText(seat.getQuota_name());
            quota_code.setText(seat.getQuota_code());
            class_name.setText(seat.getClass_name());
            class_code.setText(seat.getClass_code());

            SeatAvailabilityAdapter adapter = new SeatAvailabilityAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }
}
