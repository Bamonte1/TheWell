package com.example.thewell;

/**
 Jacob Bamonte, Mike Moran
 CIT382
 Final Project
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Item> mItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    private EditText[] quantities;
    StartActivity start;
    Random rand;

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rand = new Random();

        start = new StartActivity();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOrder(view);
            }
        });

        //Set up RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ItemAdapter(mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //Get data for Item for RecyclerView
        getData();
    }

    private void getData() {

        mItems.add(new Item(getDrawable(R.drawable.burger),
                getString(R.string.burger_description), getString(R.string.burger_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.dessert),
                getString(R.string.dessert_description), getString(R.string.dessert_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.fish_taco),
                getString(R.string.fish_taco_description), getString(R.string.fish_taco_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.coffee),
                getString(R.string.coffee_description), getString(R.string.coffee_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.pancake),
                getString(R.string.pancake_description), getString(R.string.pancake_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.scrambler),
                getString(R.string.scrambler_description), getString(R.string.scrambler_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.soup),
                getString(R.string.soup_description), getString(R.string.soup_price), rand.nextInt(6)));

        mItems.add(new Item(getDrawable(R.drawable.wrap),
                getString(R.string.wrap_description), getString(R.string.wrap_price), rand.nextInt(6)));

    }

    private void processOrder(View view) {

        String[] tempQuantities = new String[8];

        //Get EditText values from adapter
        for(int i = 0; i < mAdapter.quantities.length; i++) {
            if(mAdapter.quantities[i] != null)
                tempQuantities[i] = mAdapter.quantities[i];
            else
                tempQuantities[i] = "0";
        }

        //Send quantities to order activity and open activity
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra("ORDER", tempQuantities);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private void init() {

        Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
        finish();
        startActivity(intent);

    }

    public boolean isServicesReady() {

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS) {

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Sorry maps cannot be displayed", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void showToast (String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
