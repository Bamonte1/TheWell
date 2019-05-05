package com.example.thewell;

/**
 Jacob Bamonte, Mike Moran
 CIT382
 Final Project
 */

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

public class OrderActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get TextViews
        TextView burger = findViewById(R.id.burger_result_text);
        TextView dessert = findViewById(R.id.dessert_result_text);
        TextView fish = findViewById(R.id.fish_taco_result_text);
        TextView coffee = findViewById(R.id.coffee_result_text);
        TextView pancake = findViewById(R.id.pancake_result_text);
        TextView scrambler = findViewById(R.id.scrambler_result_text);
        TextView burgerSum = findViewById(R.id.burger_total_text);
        TextView dessertSum = findViewById(R.id.dessert_total_text);
        TextView fishSum = findViewById(R.id.fish_taco_total_text);
        TextView coffeeSum = findViewById(R.id.coffee_total_text);
        TextView pancakeSum = findViewById(R.id.pancake_total_text);
        TextView scramblerSum = findViewById(R.id.scrambler_total_text);
        TextView sum = findViewById(R.id.sum_text);

        //Get values from EditTexts
        Intent intent = getIntent();
        String[] quantities = intent.getStringArrayExtra("ORDER");

        double[] totals = new double[6];
        double total;
        NumberFormat format = NumberFormat.getCurrencyInstance();

        //Set text for quantities
        burger.setText(getString(R.string.burger_result) +  " " + quantities[0] + " = ");
        dessert.setText(getString(R.string.dessert_result) +  " " + quantities[1] + " = ");
        fish.setText(getString(R.string.fish_taco_result) +  " " + quantities[2] + " = ");
        coffee.setText(getString(R.string.coffee_result) +  " " + quantities[3] + " = ");
        pancake.setText(getString(R.string.pancake_result) +  " " + quantities[4] + " = ");
        scrambler.setText(getString(R.string.scrambler_result) +  " " + quantities[5] + " = ");

        //Calculate totals
        totals[0] = Integer.parseInt(quantities[0]) * 5.00;
        totals[1] = Integer.parseInt(quantities[1]) * 7.50;
        totals[2] = Integer.parseInt(quantities[2]) * 8.50;
        totals[3] = Integer.parseInt(quantities[3]) * 2.00;
        totals[4] = Integer.parseInt(quantities[4]) * 6.50;
        totals[5] = Integer.parseInt(quantities[5]) * 8.00;

        //Calculate sum of totals
        total = totals[0] + totals[1] + totals[2] + totals[3] + totals[4] + totals[5];

        //Set totals
        burgerSum.setText(format.format(totals[0]));
        dessertSum.setText(format.format(totals[1]));
        fishSum.setText(format.format(totals[2]));
        coffeeSum.setText(format.format(totals[3]));
        pancakeSum.setText(format.format(totals[4]));
        scramblerSum.setText(format.format(totals[5]));
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
