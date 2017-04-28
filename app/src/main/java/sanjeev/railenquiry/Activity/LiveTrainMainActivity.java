package sanjeev.railenquiry.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class LiveTrainMainActivity extends AppCompatActivity {
    private TextInputLayout inputLayoutTrainNumber;
    private EditText edt_train_number;
    private Spinner spnr_date;
    private Button btn_submit;
    CalendarSpinnerAdapter mSpinnerDateInAdapter;
    private String train_no;
    private String date1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_train_main);

        initViews();
    }

    private void initViews() {
        inputLayoutTrainNumber = (TextInputLayout) findViewById(R.id.input_layout_train_no);
        edt_train_number = (EditText) findViewById(R.id.edt_train_no);
        spnr_date = (Spinner) findViewById(R.id.spnr_date);
        btn_submit = (Button) findViewById(R.id.btn_submit);


        spnr_date.setAdapter(new CalendarSpinnerAdapter(this, 5));


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                train_no = edt_train_number.getText().toString();
                date1 = spnr_date.getSelectedItem().toString();


                Intent in = new Intent(getApplicationContext(), LiveTrainActivity.class);
                in.putExtra("train_no", train_no);
                in.putExtra("date", date1);
                startActivity(in);
            }
        });

    }


}
