package com.example.datepicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatePicker  datePickerData;
    TextView    tvData;
    Button      btnCapturarData;

    String      data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        datePickerData  = (DatePicker)  findViewById(R.id.lblData);
        tvData          = (TextView)    findViewById(R.id.dpData);
        btnCapturarData = (Button)      findViewById(R.id.cmdCapturarData);

        btnCapturarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = datePickerData.getDayOfMonth() +
                        "/" + datePickerData.getMonth() +
                        "/" + datePickerData.getYear();
                tvData.setText(data);
            }
        });
    }
}