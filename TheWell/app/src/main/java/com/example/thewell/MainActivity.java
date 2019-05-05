package com.example.thewell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 Jacob Bamonte
 CIT382
 Assignment 3 - Droid Cafe
 */

public class MainActivity extends AppCompatActivity {

    List<Item> mItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    private EditText[] quantities;
    StartActivity start;
    private final String TAG = "MainActivity: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        start = new StartActivity();

        //Fab processes EditText values
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
                getString(R.string.burger_description), getString(R.string.burger_price)));

        mItems.add(new Item(getDrawable(R.drawable.dessert),
                getString(R.string.dessert_description), getString(R.string.dessert_price)));

        mItems.add(new Item(getDrawable(R.drawable.fish_taco),
                getString(R.string.fish_taco_description), getString(R.string.fish_taco_price)));

        mItems.add(new Item(getDrawable(R.drawable.coffee),
                getString(R.string.coffee_description), getString(R.string.coffee_price)));

        mItems.add(new Item(getDrawable(R.drawable.pancake),
                getString(R.string.pancake_description), getString(R.string.pancake_price)));

        mItems.add(new Item(getDrawable(R.drawable.scrambler),
                getString(R.string.scrambler_description), getString(R.string.scrambler_price)));

        mItems.add(new Item(getDrawable(R.drawable.soup),
                getString(R.string.soup_description), getString(R.string.soup_price)));

        mItems.add(new Item(getDrawable(R.drawable.wrap),
                getString(R.string.wrap_description), getString(R.string.wrap_price)));

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
        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
        intent.putExtra("ORDER", tempQuantities);
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
                showToast("contact page");
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
}
