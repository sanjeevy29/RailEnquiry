package sanjeev.railenquiry.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import sanjeev.railenquiry.R;
import sanjeev.railenquiry.Utils.HttpHandler;

public class StationAutocompleteActivity extends AppCompatActivity {

    static List<String> responseList = new ArrayList<String>();
    private String webResponse = XmlPullParser.NO_NAMESPACE;
    private String TAG = "StationAutocompleteActivity";
    static AutoCompleteTextView textView;
    static ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_textview);
        textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);


        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 2) {
                    ArrayList<Object> objectArrayList = new ArrayList<Object>();
                    objectArrayList.add(textView.getText().toString());
                    objectArrayList.add(textView);
                    new HttpGetTask().execute(objectArrayList);
                }
            }
        });
    }

    public static class HttpGetTask extends AsyncTask<ArrayList<Object>, Void, ArrayList<Object>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
        }

        @Override
        protected ArrayList<Object> doInBackground(ArrayList<Object>... params) {
            String url = "http://api.railwayapi.com/suggest_station/name/" + String.valueOf(params[0].get(0)) + "/apikey/nn4ypiuv/";
            HttpHandler request = new HttpHandler();

            try {
                String jsonStr = request.makeServiceCall(url);
                ArrayList<Object> objectArrayList = new ArrayList<Object>();
                objectArrayList.add(jsonStr);
                objectArrayList.add(params[0].get(1));
                objectArrayList.add(params[0].get(2));

                return objectArrayList;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Object> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            try {

                JSONObject jsonObject = new JSONObject(String.valueOf(result.get(0)));
                JSONArray jsonTrain = jsonObject.getJSONArray("station");

                for (int i = 0; i < jsonTrain.length(); i++) {
                    JSONObject stationObject = jsonTrain.getJSONObject(i);

                    String name = stationObject.getString("fullname");
                    String code = stationObject.getString("code");

                    String station_code = name + " - " + code;
                    responseList.add(station_code);
                }
                Context context = (Context) result.get(2);
                adapter = new ArrayAdapter<String>(context/*MyApplication.getAppContext()*/,
                        android.R.layout.simple_dropdown_item_1line, responseList);

                AutoCompleteTextView autoTextView = (AutoCompleteTextView) result.get(1);
                autoTextView.setThreshold(1);
                autoTextView.setAdapter(adapter);

//                textView.setThreshold(1);
//                textView.setAdapter(adapter);


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


}