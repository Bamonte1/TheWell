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
        TextView burger = findViewById(R.id.burger_result_text);
        TextView dessert = findViewById(R.id.dessert_result_text);
        TextView fish = findViewById(R.id.fish_taco_result_text);
        TextView coffee = findViewById(R.id.coffee_result_text);
        TextView pancake = findViewById(R.id.pancake_result_text);
        TextView scrambler = findViewById(R.id.scrambler_result_text);
        TextView soup = findViewById(R.id.soup_result_text);
        TextView wrap = findViewById(R.id.wrap_result_text);
        TextView burgerSum = findViewById(R.id.burger_total_text);
        TextView dessertSum = findViewById(R.id.dessert_total_text);
        TextView fishSum = findViewById(R.id.fish_taco_total_text);
        TextView coffeeSum = findViewById(R.id.coffee_total_text);
        TextView pancakeSum = findViewById(R.id.pancake_total_text);
        TextView scramblerSum = findViewById(R.id.scrambler_total_text);
        TextView soupSum = findViewById(R.id.soup_total_text);
        TextView wrapSum = findViewById(R.id.wrap_total_text);
        TextView sum = findViewById(R.id.sum_text);

        //Get values from EditTexts
        Intent intent = getIntent();
        String[] quantities = intent.getStringArrayExtra("ORDER");

        double[] totals = new double[8];
        double total;
        NumberFormat format = NumberFormat.getCurrencyInstance();

        //Set text for quantities
        burger.setText(getString(R.string.burger_result) +  " " + quantities[0] + " = ");
        dessert.setText(getString(R.string.dessert_result) +  " " + quantities[1] + " = ");
        fish.setText(getString(R.string.fish_taco_result) +  " " + quantities[2] + " = ");
        coffee.setText(getString(R.string.coffee_result) +  " " + quantities[3] + " = ");
        pancake.setText(getString(R.string.pancake_result) +  " " + quantities[4] + " = ");
        scrambler.setText(getString(R.string.scrambler_result) +  " " + quantities[5] + " = ");
        soup.setText(getString(R.string.soup_result) +  " " + quantities[6] + " = ");
        wrap.setText(getString(R.string.wrap_result) +  " " + quantities[7] + " = ");

        //Calculate totals
        totals[0] = Integer.parseInt(quantities[0]) * 5.00;
        totals[1] = Integer.parseInt(quantities[1]) * 7.50;
        totals[2] = Integer.parseInt(quantities[2]) * 8.50;
        totals[3] = Integer.parseInt(quantities[3]) * 2.00;
        totals[4] = Integer.parseInt(quantities[4]) * 6.50;
        totals[5] = Integer.parseInt(quantities[5]) * 8.00;
        totals[6] = Integer.parseInt(quantities[6]) * 3.50;
        totals[7] = Integer.parseInt(quantities[7]) * 5.00;

        //Calculate sum of totals
        total = totals[0] + totals[1] + totals[2] + totals[3] + totals[4] + totals[5] + totals[6] + totals[7];

        //Set totals
        burgerSum.setText(format.format(totals[0]));
        dessertSum.setText(format.format(totals[1]));
        fishSum.setText(format.format(totals[2]));
        coffeeSum.setText(format.format(totals[3]));
        pancakeSum.setText(format.format(totals[4]));
        scramblerSum.setText(format.format(totals[5]));
        soupSum.setText(format.format(totals[6]));
        wrapSum.setText(format.format(totals[7]));
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
