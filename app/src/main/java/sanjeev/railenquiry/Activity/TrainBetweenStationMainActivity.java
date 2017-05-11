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

public class TrainBetweenStationMainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private AutoCompleteTextView edt_train_number, edt_source, edt_destination;
    private Spinner spnr_date, spnr_quota, spnr_class;
    private Button btn_submit;

    String t_source, t_dest;
    String[] date = {"10-05", "11-05", "12-05", "13-05", "14-05", "15-05"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_between_station_main_layout);

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
                    objectArrayList.add(TrainBetweenStationMainActivity.this);
                    new StationAutocompleteActivity.HttpGetTask().execute(objectArrayList);
                }
            }
        });

        edt_destination.addTextChangedListener(new TextWatcher() {
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
                    objectArrayList.add(edt_destination.getText().toString());
                    objectArrayList.add(edt_destination);
                    objectArrayList.add(TrainBetweenStationMainActivity.this);
                    new StationAutocompleteActivity.HttpGetTask().execute(objectArrayList);
                }
            }
        });
    }

    private void initView() {

        edt_train_number = (AutoCompleteTextView) findViewById(R.id.act_train_no);
        edt_source = (AutoCompleteTextView) findViewById(R.id.atc_source);
        edt_destination = (AutoCompleteTextView) findViewById(R.id.act_dest);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        spnr_date = (Spinner) findViewById(R.id.spnr_date);


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, date);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnr_date.setAdapter(aa);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      train_no = edt_train_number.getText().toString();

                t_source = edt_source.getText().toString();
                t_dest = edt_destination.getText().toString();


                Intent in = new Intent(getApplicationContext(), TrainBetweenStationActivity.class);

                in.putExtra("t_source", t_source);
                in.putExtra("t_dest", t_dest);

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
