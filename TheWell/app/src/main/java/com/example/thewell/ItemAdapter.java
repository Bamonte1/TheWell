package com.example.thewell;

/**
 Jacob Bamonte, Mike Moran
 CIT382
 Final Project
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public String[] quantities = new String[6];

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    //Set up view
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_list_row, viewGroup, false);

        return new ItemViewHolder(itemView);
    }

    //Get and set data from Item for RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.image.setImageDrawable(itemList.get(i).getImage());
        itemViewHolder.desc.setText(itemList.get(i).getDesc());
        itemViewHolder.price.setText(itemList.get(i).getPrice());
        itemViewHolder.rate.setRating(itemList.get(i).getRate());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView desc;
        public TextView price;
        public EditText quantity;
        public RatingBar rate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //Set up TextViews, EditTexts, and ImageViews
            image = (ImageView) itemView.findViewById(R.id.menu_pic);
            desc = (TextView) itemView.findViewById(R.id.menu_desc_text);
            price = (TextView) itemView.findViewById(R.id.menu_price_text);
            quantity = (EditText) itemView.findViewById(R.id.menu_quantity);
            rate = (RatingBar) itemView.findViewById(R.id.ratingBar);

            //Set up listener for EditTexts to get values
            quantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    image.findViewById(R.id.menu_pic);
                    Animation myAnim = AnimationUtils.loadAnimation(image.getContext() ,R.anim.bounce_select);
                    MyInterpolator interpolator = new MyInterpolator(0.3, 15);
                    myAnim.setInterpolator(interpolator);

                    image.startAnimation(myAnim);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    quantities[getAdapterPosition()] = s.toString();
                }
            });
        }
    }
}
