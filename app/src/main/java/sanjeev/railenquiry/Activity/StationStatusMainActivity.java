package sanjeev.railenquiry.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class StationStatusMainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private AutoCompleteTextView edt_source;
    private Spinner spnr_time;
    private Button btn_submit;

    String t_source;

    String[] time = {"Within 2 Hours", "Within 3 Hours", "Within 4 Hours","Within 5 Hours","Within 6 Hours",
            "Within 7 Hours","Within 8 Hours"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_status_main_layout);

        initView();
        StationAutocompleteActivity s = new StationAutocompleteActivity();
        edt_source.addTextChangedListener(new TextWatcher() {
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
                    objectArrayList.add(edt_source.getText().toString());
                    objectArrayList.add(edt_source);
                    objectArrayList.add(StationStatusMainActivity.this);
                    new StationAutocompleteActivity.HttpGetTask().execute(objectArrayList);
                }
            }
        });

    }

    private void initView() {


        edt_source = (AutoCompleteTextView) findViewById(R.id.atc_source);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        spnr_time = (Spinner) findViewById(R.id.spnr_time);


        spnr_time.setOnItemSelectedListener(this);



        ArrayAdapter time_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, time);

        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnr_time.setAdapter(time_adapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t_source = edt_source.getText().toString();



                Intent in = new Intent(getApplicationContext(), StationStatusMainActivity.class);

                in.putExtra("t_source", t_source);

                startActivity(in);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
