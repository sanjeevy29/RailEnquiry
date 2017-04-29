package sanjeev.railenquiry.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sanjeev.railenquiry.R;

/**
 * Created by sanjeev.yadav on 4/29/2017.
 */

public class PNRMainActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutPNRNumber;
    private EditText edt_pnr_number;
    private Button btn_submit;
    String pnr_no;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pnr_main_layout);

        inputLayoutPNRNumber = (TextInputLayout) findViewById(R.id.input_layout_pnr_no);
        edt_pnr_number = (EditText) findViewById(R.id.edt_pnr_no);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pnr_no = edt_pnr_number.getText().toString();

                Intent in = new Intent(PNRMainActivity.this,PNRActivity.class);
                in.putExtra("pnr_no",pnr_no);
                startActivity(in);

            }
        });

    }
}
