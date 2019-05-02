package com.example.thewell;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST = 9001;

    DatePicker date;

    int dateDay, dateMonth, dateYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = findViewById(R.id.datePicker);

        date.setMinDate(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dateYear = year;
                    dateDay = dayOfMonth;
                    dateMonth = monthOfYear;
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(item.getItemId()) {
            case R.id.action_phone:
                showToast("(570) 246-5585");
                return true;
            case R.id.action_home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.action_mail:
                showToast("kyle.anspach@cwc.life");
                return true;
            case R.id.action_map:
                if(isServicesReady()) {
                    init();
                }
                return true;
            case R.id.action_sound:
                showToast("sound");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast (String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void init() {

        Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
        finish();
        startActivity(intent);

    }

    public boolean isServicesReady() {

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(InfoActivity.this);

        if(available == ConnectionResult.SUCCESS) {

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(InfoActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Sorry maps cannot be displayed", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
