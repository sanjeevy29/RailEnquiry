package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sanjeev.railenquiry.Adapter.PNRAdapter;
import sanjeev.railenquiry.Adapter.ScheduleAdapter;
import sanjeev.railenquiry.Model.PNR;
import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/29/2017.
 */

public class PNRActivity extends AppCompatActivity {
    RecyclerView passenger_recycler;
    List<PNR> data;
    PNR pnrdetails;
    private String TAG = PNRActivity.class.getSimpleName();
    TextView train_pnr_no, train_no, train_name, chart_prepared, ticket_from, ticket_to, boarding, destination, doj, train_class, chart;
    String pnr_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pnr_layout);

        initView();
        new GetPNRDetails().execute();
    }

    private void initView() {
        pnr_no = getIntent().getExtras().getString("pnr_no");
        train_pnr_no = (TextView) findViewById(R.id.train_pnr_no);
        train_no = (TextView) findViewById(R.id.train_no);
        train_name = (TextView) findViewById(R.id.train_name);
        chart_prepared = (TextView) findViewById(R.id.chart_prepared);
        ticket_from = (TextView) findViewById(R.id.ticket_from);
        ticket_to = (TextView) findViewById(R.id.ticket_to);
        boarding = (TextView) findViewById(R.id.boarding);
        destination = (TextView) findViewById(R.id.destination);
        doj = (TextView) findViewById(R.id.doj);
        train_class = (TextView) findViewById(R.id.train_class);
        chart = (TextView) findViewById(R.id.chart);
        passenger_recycler = (RecyclerView) findViewById(R.id.recyler_passengerdetails);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        passenger_recycler.setHasFixedSize(true);
        passenger_recycler.setLayoutManager(layoutManager);
    }

    private class GetPNRDetails extends AsyncTask<String, Void, String> {

        String url = "http://api.railwayapi.com/pnr_status/pnr/" + pnr_no + "/apikey/nn4ypiuv/";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();


            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();

            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject jsonboarding = jsonObject.getJSONObject("boarding_point");
                    JSONObject jsonreservation = jsonObject.getJSONObject("reservation_upto");
                    JSONObject jsonfrom = jsonObject.getJSONObject("from_station");
                    JSONObject jsonto = jsonObject.getJSONObject("to_station");

                    pnrdetails = new PNR();

                    String train_no = jsonObject.getString("train_num");
                    String train_name = jsonObject.getString("train_name");
                    String pnr = jsonObject.getString("pnr");
                    String doj = jsonObject.getString("doj");
                    String train_class = jsonObject.getString("class");
                    String chart_prep = jsonObject.getString("chart_prepared");
                    String total_passenger = jsonObject.getString("total_passengers");

                    String bname = jsonboarding.getString("name");
                    String rname = jsonreservation.getString("name");
                    String fname = jsonfrom.getString("name");
                    String tname = jsonto.getString("name");


                    JSONArray passengerArray = jsonObject.getJSONArray("passengers");


                    for (int j = 0; j < passengerArray.length(); j++) {
                        JSONObject r = passengerArray.getJSONObject(j);

                        pnrdetails = new PNR();


                        String current_status = r.getString("current_status");
                        String booking_status = r.getString("booking_status");
                        String srno = r.getString("no");

                        pnrdetails.setCurrent_status(current_status);
                        pnrdetails.setBooking_status(booking_status);
                        pnrdetails.setSrno(srno);
                        pnrdetails.setChart_prepared(chart_prep);
                        pnrdetails.setTrain_class(train_class);
                        pnrdetails.setDoj(doj);

                        data.add(pnrdetails);

                    }


                    pnrdetails.setTrain_no(train_no);
                    pnrdetails.setTrain_name(train_name);
                    pnrdetails.setPnr(pnr);
                    pnrdetails.setTotal_passenger(total_passenger);
                    pnrdetails.setTicketfrom(fname);
                    pnrdetails.setTicketto(tname);
                    pnrdetails.setBoarding(bname);
                    pnrdetails.setDestination(rname);

                    data.add(pnrdetails);

                } catch (final JSONException e) {
                    e.printStackTrace();

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");


            }
//            webResponse = contactList.toString();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            train_pnr_no.setText(pnrdetails.getPnr());
            train_no.setText(pnrdetails.getTrain_no());
            train_name.setText(pnrdetails.getTrain_name());
            chart_prepared.setText(pnrdetails.getChart_prepared());
            ticket_from.setText(pnrdetails.getTicketfrom());
            ticket_to.setText(pnrdetails.getTicketto());
            boarding.setText(pnrdetails.getBoarding());
            destination.setText(pnrdetails.getDestination());
            doj.setText(pnrdetails.getDoj());
            train_class.setText(pnrdetails.getTrain_class());
            chart.setText(pnrdetails.getChart_prepared());


            PNRAdapter adapter = new PNRAdapter(getApplicationContext(), data);
            passenger_recycler.setAdapter(adapter);


        }

    }


}
