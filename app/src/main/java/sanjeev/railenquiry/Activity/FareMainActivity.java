package sanjeev.railenquiry.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class FareMainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    private TextInputLayout inputLayoutTrainNumber, inputLayoutSource, inputLayoutDestination;
    private EditText edt_train_number, edt_source, edt_destination;
    private Spinner spnr_date, spnr_quota, spnr_class;
    private Button btn_submit;

    String[] train_class = {"All Classes", "SL - Sleeper", "3A - Third AC", "2A - Second AC", "1A - First AC"
    ,"2S - Second Seating","CC - AC Chair Car","FC - First Class","3E - Third AC Economy"
    };
    String[] train_quota = {"General Quota","Tatkal Quota","Premium Tatkal"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fare_main_layout);

        initView();
    }

    private void initView() {
        inputLayoutTrainNumber = (TextInputLayout) findViewById(R.id.input_layout_train_no);
        inputLayoutSource = (TextInputLayout) findViewById(R.id.input_layout_source);
        inputLayoutDestination = (TextInputLayout) findViewById(R.id.input_layout_dest);
        edt_train_number = (EditText) findViewById(R.id.edt_train_no);
        edt_source = (EditText) findViewById(R.id.edt_source);
        edt_destination = (EditText) findViewById(R.id.edt_dest);

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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
