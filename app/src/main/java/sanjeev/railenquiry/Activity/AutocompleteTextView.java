package sanjeev.railenquiry.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

public class AutocompleteTextView extends AppCompatActivity {

    List<String> responseList = new ArrayList<String>();
    private String webResponse = XmlPullParser.NO_NAMESPACE;
    private String TAG = "MainActivity";
    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter = null;

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
                if(s.length() == 2){
                    new HttpGetTask().execute();
                }
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {
        String url = "http://api.railwayapi.com/suggest_train/trains/"+textView.getText().toString()+"/apikey/nn4ypiuv/";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
        }

        @Override
        protected String doInBackground(String... params) {
            HttpHandler request = new HttpHandler();
            String jsonStr = request.makeServiceCall(url);
            try {
                return jsonStr;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonTrain = jsonObject.getJSONArray("train");

                for (int i = 0; i < jsonTrain.length(); i++) {
                    String name = jsonTrain.getString(i);
                    responseList.add(name);
                }

                adapter = new ArrayAdapter<String>(AutocompleteTextView.this,
                        android.R.layout.simple_dropdown_item_1line, responseList);
                textView.setThreshold(1);
                textView.setAdapter(adapter);


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


}