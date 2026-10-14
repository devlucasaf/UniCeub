package com.exemplo.postlayout;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class PostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardView cardView = new CardView(this);
        CardView.LayoutParams cardParams = new CardView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(12);               
        cardView.setCardElevation(4);         
        cardView.setUseCompatPadding(true);

        LinearLayout column = new LinearLayout(this);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setPadding(16, 16, 16, 16);
        column.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        ImageView imageView = new ImageView(this);
        Drawable placeholder = getResources().getDrawable(android.R.drawable.ic_menu_gallery);
        imageView.setImageDrawable(placeholder);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                200  
        );
        imgParams.setMargins(0, 0, 0, 12);
        imageView.setLayoutParams(imgParams);
        column.addView(imageView);

        TextView title = new TextView(this);
        title.setText("Leiautes criativos com Flutter");
        title.setTextSize(20);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        titleParams.setMargins(0, 0, 0, 8);
        title.setLayoutParams(titleParams);
        column.addView(title);

        TextView dateTime = new TextView(this);
        dateTime.setText("04/08/2021 – 17:55");
        dateTime.setTextSize(14);
        dateTime.setTextColor(Color.GRAY);
        LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dateParams.setMargins(0, 0, 0, 12);
        dateTime.setLayoutParams(dateParams);
        column.addView(dateTime);

        TextView description = new TextView(this);
        description.setText("Conheça o Flutter, um framework para desenvolvimento mobile, web e desktop");
        description.setTextSize(16);
        description.setTextColor(Color.DKGRAY);
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        description.setLayoutParams(descParams);
        column.addView(description);

        cardView.addView(column);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(cardView);

        setContentView(scrollView);
    }
}