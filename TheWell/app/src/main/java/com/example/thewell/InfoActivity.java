package com.example.thewell;

/**
 Jacob Bamonte, Mike Moran
 CIT382
 Final Project
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int ERROR_DIALOG_REQUEST = 9001;

    DatePicker date;
    EditText nameText, addressText, cityText, stateText, phoneText, noteText;
    Spinner spinner;

    int dateDay, dateMonth, dateYear;
    String delivery, phoneType;
    String[] quantities;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        quantities = intent.getStringArrayExtra("ORDER");

        nameText = findViewById(R.id.nameText);
        addressText = findViewById(R.id.addressInfoText);
        cityText = findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        phoneText = findViewById(R.id.phoneText);
        noteText = findViewById(R.id.noteText);

        spinner = findViewById(R.id.labelSpinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        phoneType = "Home";
        delivery = "Delivery";

        date = findViewById(R.id.datePicker);

        date.setMinDate(System.currentTimeMillis());
        date.setEnabled(false);
        dateDay = calendar.get(calendar.DAY_OF_MONTH);
        dateMonth = calendar.get(calendar.MONTH) + 1;
        dateYear = calendar.get(calendar.YEAR);

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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.delivery:
                if (checked) {
                    delivery = "Delivery";
                    date.setEnabled(false);
                    dateDay = calendar.DAY_OF_MONTH;
                    dateMonth = calendar.MONTH + 1;
                    dateYear = calendar.YEAR;
                }
                break;
            case R.id.pickup:
                if (checked) {
                    delivery = "Pickup";
                    date.setEnabled(true);
                }
                break;
            default:
                break;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        phoneType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void done(View view) {

        Database db = new Database(this);

        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String city = cityText.getText().toString();
        String state = stateText.getText().toString();
        String phone = phoneText.getText().toString();
        String notes = noteText.getText().toString();

        db.addInfo(new UserInfo(name, address, city, state, phone, phoneType, notes, delivery,
                dateMonth + "/" + dateDay + "/" + dateYear));

        List<UserInfo> info = db.getAllInfo();

        for(UserInfo i : info) {
            Log.d("Info", i.toString());
        }

        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
        intent.putExtra("ORDER", quantities);
        finish();
        startActivity(intent);

    }

}
