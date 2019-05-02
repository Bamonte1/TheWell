package com.example.thewell;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.text.NumberFormat;

/**
 Jacob Bamonte
 CIT382
 Assignment 3 - Droid Cafe
 */

public class OrderActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get TextViews
        TextView donut = findViewById(R.id.donut_result_text);
        TextView froyo = findViewById(R.id.froyo_result_text);
        TextView ice = findViewById(R.id.icecream_result_text);
        TextView coffee = findViewById(R.id.coffee_result_text);
        TextView chocolate = findViewById(R.id.chocolate_result_text);
        TextView donutSum = findViewById(R.id.donut_total_text);
        TextView froyoSum = findViewById(R.id.froyo_total_text);
        TextView iceSum = findViewById(R.id.icecream_total_text);
        TextView coffeeSum = findViewById(R.id.coffee_total_text);
        TextView chocolateSum = findViewById(R.id.chocolate_total_text);
        TextView sum = findViewById(R.id.sum_text);

        //Get values from EditTexts
        Intent intent = getIntent();
        String[] quantities = intent.getStringArrayExtra("ORDER");

        double[] totals = new double[5];
        double total;
        NumberFormat format = NumberFormat.getCurrencyInstance();

        //Set text for quantities
        donut.setText(getString(R.string.donut_result) +  " " + quantities[0] + " = ");
        froyo.setText(getString(R.string.froyo_result) +  " " + quantities[1] + " = ");
        ice.setText(getString(R.string.icecream_result) +  " " + quantities[2] + " = ");
        coffee.setText(getString(R.string.coffee_result) +  " " + quantities[3] + " = ");
        chocolate.setText(getString(R.string.chocolate_result) +  " " + quantities[4] + " = ");

        //Calculate totals
        totals[0] = Integer.parseInt(quantities[0]) * 1.50;
        totals[1] = Integer.parseInt(quantities[1]) * 0.50;
        totals[2] = Integer.parseInt(quantities[2]) * 1.00;
        totals[3] = Integer.parseInt(quantities[3]) * 2.00;
        totals[4] = Integer.parseInt(quantities[4]) * 0.25;

        //Calculate sum of totals
        total = totals[0] + totals[1] + totals[2] + totals[3] + totals[4];

        //Set totals
        donutSum.setText(format.format(totals[0]));
        froyoSum.setText(format.format(totals[1]));
        iceSum.setText(format.format(totals[2]));
        coffeeSum.setText(format.format(totals[3]));
        chocolateSum.setText(format.format(totals[4]));
        sum.setText(format.format(total));
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

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(OrderActivity.this);

        if(available == ConnectionResult.SUCCESS) {

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(OrderActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Sorry maps cannot be displayed", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Close order activity
    public void launchMainActivity(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);

    }
}
