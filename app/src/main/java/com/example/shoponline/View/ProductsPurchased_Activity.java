package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.shoponline.R;

import java.util.Calendar;

public class ProductsPurchased_Activity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button btnDateFrom,btnDateTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_purchased);

        initDatePicker();
        btnDateFrom = findViewById(R.id.dateFrom);

        btnDateFrom.setText(getTodayDate());
        btnDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        btnDateTo = findViewById(R.id.dateTo);

        btnDateTo.setText(getTodayDate());
        btnDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);

    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day,month,year);
                btnDateFrom.setText(date);
                btnDateTo.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int year, int month, int day){
        return getMonthFormat (month) +"/"+ day + "/"+year;
    }

    private String getMonthFormat(int month) {
        if(month == 1){return "01";}
        if(month == 2){return "02";}
        if(month == 3){return "03";}
        if(month == 4){return "04";}
        if(month == 5){return "05";}
        if(month == 6){return "06";}
        if(month == 7){return "07";}
        if(month == 8){return "08";}
        if(month == 9){return "09";}
        if(month == 10){return "10";}
        if(month == 11){return "11";}
        if(month == 12){return "12";}
        return "01";
    }


}