package com.example.thewell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 Jacob Bamonte
 CIT382
 Assignment 3 - Droid Cafe
 */

public class OrderActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

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

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Close order activity
    public void launchMainActivity(View view) {
        finish();
    }
}
