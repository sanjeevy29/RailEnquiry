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

import sanjeev.railenquiry.Model.SeatAvailability;
import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class SeatAvailabilityMainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private AutoCompleteTextView edt_train_number, edt_source, edt_destination;
    private Spinner spnr_date, spnr_quota, spnr_class;
    private Button btn_submit;
    String t_class;
    String t_quota;
    String t_source, t_dest, t_train_no;

    String[] train_class = {"All Classes", "SL - Sleeper", "3A - Third AC", "2A - Second AC", "1A - First AC"
            , "2S - Second Seating", "CC - AC Chair Car", "FC - First Class", "3E - Third AC Economy"
    };
    String[] train_quota = {"General Quota", "Tatkal Quota", "Premium Tatkal"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_availability_main_layout);

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
                    objectArrayList.add(SeatAvailabilityMainActivity.this);
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
                    objectArrayList.add(SeatAvailabilityMainActivity.this);
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
        spnr_class = (Spinner) findViewById(R.id.spnr_class);
        spnr_quota = (Spinner) findViewById(R.id.spnr_quota);

        spnr_quota.setOnItemSelectedListener(this);
        spnr_class.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, train_class);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnr_class.setAdapter(aa);


        ArrayAdapter quota_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, train_quota);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnr_quota.setAdapter(quota_adapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      train_no = edt_train_number.getText().toString();
                t_class = spnr_class.getSelectedItem().toString();
                t_quota = spnr_quota.getSelectedItem().toString();
                t_source = edt_source.getText().toString();
                t_dest = edt_destination.getText().toString();
                t_train_no = edt_train_number.getText().toString();

                if (t_quota.equalsIgnoreCase("General Quota")) {
                    t_quota = "GN";
                }

                Intent in = new Intent(getApplicationContext(), SeatAvailabilityActivity.class);
                in.putExtra("t_class", t_class);
                in.putExtra("t_quota", t_quota);
                in.putExtra("t_source", t_source);
                in.putExtra("t_dest", t_dest);
                in.putExtra("t_train_no", t_train_no);
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
