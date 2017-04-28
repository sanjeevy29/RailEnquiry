package sanjeev.railenquiry.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class FareMainActivity extends AppCompatActivity {
    private TextInputLayout inputLayoutTrainNumber,inputLayoutSource,inputLayoutDestination;
    private EditText edt_train_number,edt_source,edt_destination;
    private Spinner spnr_date,spnr_quota,spnr_class;
    private Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fare_main_layout);

        initView();
    }

    private void initView()
    {
        inputLayoutTrainNumber = (TextInputLayout) findViewById(R.id.input_layout_train_no);
        inputLayoutSource = (TextInputLayout) findViewById(R.id.input_layout_source);
        inputLayoutDestination = (TextInputLayout) findViewById(R.id.input_layout_dest);
        edt_train_number = (EditText) findViewById(R.id.edt_train_no);
        edt_source = (EditText) findViewById(R.id.edt_source);
        edt_destination = (EditText) findViewById(R.id.edt_dest);

        spnr_date = (Spinner) findViewById(R.id.spnr_date);
        spnr_class = (Spinner) findViewById(R.id.spnr_class);
        spnr_quota = (Spinner) findViewById(R.id.spnr_quota);

    }
}
