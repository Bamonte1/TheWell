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
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
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

        /*editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "EditorActionDetected");
                Toast.makeText(getApplicationContext(),"OnEditorWorking",Toast.LENGTH_LONG).show();
                if(actionId == EditorInfo.IME_ACTION_DONE){


                }
                return false;
            }
        });*/
    }

    private void getData() {
        mItems.add(new Item(getDrawable(R.drawable.donut_circle),
                getString(R.string.donut_description), getString(R.string.donut_price)));

        mItems.add(new Item(getDrawable(R.drawable.froyo_circle),
                getString(R.string.froyo_description), getString(R.string.froyo_price)));

        mItems.add(new Item(getDrawable(R.drawable.icecream_circle),
                getString(R.string.icecream_description), getString(R.string.icecream_price)));

        mItems.add(new Item(getDrawable(R.drawable.coffee_circle),
                getString(R.string.coffee_description), getString(R.string.coffee_price)));

        mItems.add(new Item(getDrawable(R.drawable.chocolate_circle),
                getString(R.string.chocolate_description), getString(R.string.chocolate_price)));

    }

    private void processOrder(View view) {

        String[] tempQuantities = new String[5];

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


    public void playAnimation(View view) {
        Log.d(TAG,"playAnimationStarted");
        View menuList = getLayoutInflater().inflate(R.layout.menu_list_row, null);
        ImageView image = menuList.findViewById(R.id.menu_pic);
        EditText editText = menuList.findViewById(R.id.menu_quantity);

        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_select);
        Log.d(TAG, "IME_DONE");
        // use bounce interpolator with amp .2 and freq of 20
        MyInterpolator interpolator = new MyInterpolator(0.333, 10);
        myAnim.setInterpolator(interpolator);

        image.startAnimation(myAnim);
    }
}
