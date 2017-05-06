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
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class FareActivity extends AppCompatActivity {


    String t_quota;
    String t_class;
    String t_trainno;
    String t_source;
    String t_destination;

    Fare fare;
    List<Fare> data;
    private String TAG = FareActivity.class.getSimpleName();
    TextView train_fare_name, train_no, train_name, train_from, train_to, quota_code, quota_name;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fare_recycler);

        t_class = getIntent().getExtras().getString("t_class");
        t_quota = getIntent().getExtras().getString("t_quota");
        t_trainno = getIntent().getExtras().getString("t_train_no");
        t_source = getIntent().getExtras().getString("t_source");
        t_destination = getIntent().getExtras().getString("t_dest");
        train_fare_name = (TextView) findViewById(R.id.train_fare_name);
        train_no = (TextView) findViewById(R.id.train_no);
        train_name = (TextView) findViewById(R.id.train_name);
        train_from = (TextView) findViewById(R.id.train_from);
        train_to = (TextView) findViewById(R.id.train_to);
        quota_code = (TextView) findViewById(R.id.quota_code);
        quota_name = (TextView) findViewById(R.id.quota_name);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_faredetails);


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

            String url = "http://api.railwayapi.com/fare/train/"+t_trainno+"/source/"+t_source+"/dest/"+t_destination+"/age/23/quota/" + t_quota + "/doj/05-05-2017/apikey/nn4ypiuv/";
            String jsonStr = sh.makeServiceCall(url);
            data = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject jsonfrom = jsonObject.getJSONObject("from");
                    JSONObject jsonto = jsonObject.getJSONObject("to");
                    JSONObject jsontrain = jsonObject.getJSONObject("train");
                    JSONObject jsonquota = jsonObject.getJSONObject("quota");
                    JSONArray fareArray = jsonObject.getJSONArray("fare");


                    fare = new Fare();

                    String from = jsonfrom.getString("name");
                    String to = jsonto.getString("name");

                    String tain_no = jsontrain.getString("number");
                    String train_name = jsontrain.getString("name");

                    String quota_name = jsonquota.getString("name");
                    String quota_code = jsonquota.getString("code");


                    for (int i = 0; i < fareArray.length(); i++) {

                        Fare f = new Fare();

                        JSONObject fareObject = fareArray.getJSONObject(i);

                        String fare_name = fareObject.getString("name");
                        String fare_code = fareObject.getString("code");
                        String fare_price = fareObject.getString("fare");

                        f.setFare_code(fare_code);
                        f.setFare_name(fare_name);
                        f.setFare_price(fare_price);


                        data.add(f);


                    }

                    fare.setTo(to);
                    fare.setFrom(from);
                    fare.setTrain_name(train_name);
                    fare.setTrain_no(tain_no);
                    fare.setQuota_code(quota_code);
                    fare.setQuota_name(quota_name);

                    data.add(fare);

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


            train_from.setText(fare.getFrom());
            train_to.setText(fare.getTo());
            train_name.setText(fare.getTrain_name());
            train_no.setText(fare.getTrain_no());
            quota_name.setText(fare.getQuota_name());
            quota_code.setText(fare.getQuota_code());

            FareAdapter adapter = new FareAdapter(getApplicationContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }
}
