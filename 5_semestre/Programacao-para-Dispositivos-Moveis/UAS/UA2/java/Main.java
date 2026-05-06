package com.example.flutterparajava;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class Main extends AppCompatActivity {

    private int counter = 0;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(16, 16, 16, 16);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        CardView containerDecorado = createDecoratedContainer();
        mainLayout.addView(containerDecorado);

        addVerticalSpacing(mainLayout, 20);

        TextView rowColumnTitle = new TextView(this);
        rowColumnTitle.setText("Layout com Row e Column:");
        rowColumnTitle.setTextSize(18);
        rowColumnTitle.setTypeface(rowColumnTitle.getTypeface(), android.graphics.Typeface.BOLD);
        mainLayout.addView(rowColumnTitle);

        addVerticalSpacing(mainLayout, 8);

        LinearLayout iconRow = createIconRow();
        mainLayout.addView(iconRow);

        addVerticalSpacing(mainLayout, 20);

        TextView expandedTitle = new TextView(this);
        expandedTitle.setText("Expanded ocupando espaço:");
        expandedTitle.setTextSize(18);
        expandedTitle.setTypeface(expandedTitle.getTypeface(), android.graphics.Typeface.BOLD);
        mainLayout.addView(expandedTitle);

        addVerticalSpacing(mainLayout, 8);

        LinearLayout expandedExample = createExpandedExample();
        mainLayout.addView(expandedExample);

        addVerticalSpacing(mainLayout, 20);

        TextView stackTitle = new TextView(this);
        stackTitle.setText("Exemplo de Stack:");
        stackTitle.setTextSize(18);
        stackTitle.setTypeface(stackTitle.getTypeface(), android.graphics.Typeface.BOLD);
        mainLayout.addView(stackTitle);

        addVerticalSpacing(mainLayout, 8);

        FrameLayout stackExample = createStackExample();
        mainLayout.addView(stackExample);

        addVerticalSpacing(mainLayout, 20);

        TextView counterTitle = new TextView(this);
        counterTitle.setText("Contador interativo:");
        counterTitle.setTextSize(18);
        counterTitle.setTypeface(counterTitle.getTypeface(), android.graphics.Typeface.BOLD);
        mainLayout.addView(counterTitle);

        addVerticalSpacing(mainLayout, 8);

        LinearLayout counterLayout = createCounterLayout();
        mainLayout.addView(counterLayout);

        addVerticalSpacing(mainLayout, 20);

        TextView customWidgetTitle = new TextView(this);
        customWidgetTitle.setText("Widget customizado (MeuAppBar reutilizado no corpo):");
        customWidgetTitle.setTextSize(18);
        customWidgetTitle.setTypeface(customWidgetTitle.getTypeface(), android.graphics.Typeface.BOLD);
        mainLayout.addView(customWidgetTitle);

        addVerticalSpacing(mainLayout, 8);

        MeuAppBar appBarInBody = new MeuAppBar(this, "Barra customizada dentro do corpo");
        mainLayout.addView(appBarInBody);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        MeuAppBar officialAppBar = new MeuAppBar(this, "App Demo - Flutter Fundamentos");
        root.addView(officialAppBar);

        android.widget.ScrollView scrollView = new android.widget.ScrollView(this);
        scrollView.addView(mainLayout);
        root.addView(scrollView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1.0f
        ));

        setContentView(root);

        ImageButton fab = new ImageButton(this);
        fab.setImageDrawable(ContextCompat.getDrawable(this, android.R.drawable.ic_input_add));
        fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.BLUE));
        fab.setPadding(16, 16, 16, 16);
        FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        fabParams.gravity = Gravity.BOTTOM | Gravity.END;
        fabParams.setMargins(0, 0, 16, 16);
        fab.setOnClickListener(v -> incrementCounter());
        ((FrameLayout) ((ViewGroup) root.getParent())).addView(fab, fabParams);
    }

    private CardView createDecoratedContainer() {
        CardView card = new CardView(this);
        card.setRadius(12);
        card.setCardElevation(4);
        card.setContentPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                100
        );
        card.setLayoutParams(params);

        TextView text = new TextView(this);
        text.setText("Container Decorado");
        text.setTextSize(20);
        text.setTypeface(text.getTypeface(), android.graphics.Typeface.BOLD);
        text.setTextColor(Color.BLUE);
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.parseColor("#E3F2FD")); 
        text.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        card.addView(text);
        return card;
    }

    private LinearLayout createIconRow() {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        row.addView(createIconCard(android.R.drawable.ic_menu_edit, "Curtir", Color.GREEN));
        row.addView(createIconCard(android.R.drawable.ic_menu_send, "Comentar", Color.parseColor("#FF9800")));
        row.addView(createIconCard(android.R.drawable.ic_menu_share, "Compartilhar", Color.BLUE));
        return row;
    }

    private CardView createIconCard(int iconRes, String label, int color) {
        CardView card = new CardView(this);
        card.setRadius(12);
        card.setCardElevation(2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);
        card.setLayoutParams(params);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER);
        content.setPadding(12, 8, 12, 8);

        ImageView icon = new ImageView(this);
        icon.setImageResource(iconRes);
        icon.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
        icon.setLayoutParams(new LinearLayout.LayoutParams(64, 64));

        TextView text = new TextView(this);
        text.setText(label);
        text.setTextColor(color);
        text.setTextSize(14);

        content.addView(icon);
        content.addView(text);
        card.addView(content);
        return card;
    }

    private LinearLayout createExpandedExample() {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setBackgroundColor(Color.parseColor("#EEEEEE"));
        container.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                80
        ));

        View leftRed = new View(this);
        leftRed.setBackgroundColor(Color.RED);
        leftRed.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView expandedText = new TextView(this);
        expandedText.setText("Expanded ocupa o espaço restante");
        expandedText.setGravity(Gravity.CENTER);
        expandedText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));

        View rightRed = new View(this);
        rightRed.setBackgroundColor(Color.RED);
        rightRed.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));

        container.addView(leftRed);
        container.addView(expandedText);
        container.addView(rightRed);
        return container;
    }

    private FrameLayout createStackExample() {
        FrameLayout stack = new FrameLayout(this);
        stack.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                120
        ));

        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#BBDEFB"));
        FrameLayout.LayoutParams bgParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        stack.addView(background, bgParams);

        TextView centerText = new TextView(this);
        centerText.setText("Texto no centro");
        centerText.setTextSize(16);
        centerText.setTypeface(centerText.getTypeface(), android.graphics.Typeface.BOLD);
        centerText.setBackgroundColor(Color.parseColor("#B3FFFFFF"));
        centerText.setPadding(8, 4, 8, 4);
        FrameLayout.LayoutParams centerParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        centerParams.gravity = Gravity.CENTER;
        stack.addView(centerText, centerParams);

        ImageView star = new ImageView(this);
        star.setImageResource(android.R.drawable.btn_star_big_on);
        star.setColorFilter(Color.parseColor("#FFB300"), android.graphics.PorterDuff.Mode.SRC_IN);
        FrameLayout.LayoutParams starParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        starParams.gravity = Gravity.BOTTOM | Gravity.END;
        starParams.setMargins(0, 0, 16, 16);
        stack.addView(star, starParams);

        return stack;
    }

    private LinearLayout createCounterLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        ImageButton resetBtn = new ImageButton(this);
        resetBtn.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        resetBtn.setBackgroundColor(Color.TRANSPARENT);
        resetBtn.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
        resetBtn.setOnClickListener(v -> resetCounter());

        counterTextView = new TextView(this);
        counterTextView.setText("0");
        counterTextView.setTextSize(48);
        counterTextView.setTypeface(counterTextView.getTypeface(), android.graphics.Typeface.BOLD);
        counterTextView.setPadding(20, 0, 20, 0);

        ImageButton incBtn = new ImageButton(this);
        incBtn.setImageResource(android.R.drawable.ic_input_add);
        incBtn.setBackgroundColor(Color.TRANSPARENT);
        incBtn.setOnClickListener(v -> incrementCounter());

        layout.addView(resetBtn);
        layout.addView(counterTextView);
        layout.addView(incBtn);
        return layout;
    }

    private void incrementCounter() {
        counter++;
        updateCounterText();
    }

    private void resetCounter() {
        counter = 0;
        updateCounterText();
    }

    private void updateCounterText() {
        if (counterTextView != null) {
            counterTextView.setText(String.valueOf(counter));
        }
    }

    private void addVerticalSpacing(LinearLayout parent, int dp) {
        View spacer = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp
        );
        spacer.setLayoutParams(params);
        parent.addView(spacer);
    }

    // --- CLASSE PERSONALIZADA MeuAppBar ---
    private class MeuAppBar extends LinearLayout {
        public MeuAppBar(MainActivity context, String titleText) {
            super(context);
            setOrientation(HORIZONTAL);
            setGravity(Gravity.CENTER_VERTICAL);
            setBackgroundColor(Color.parseColor("#1976D2")); 
            setElevation(4);
            setPadding(8, 0, 8, 0);
            setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    56
            ));

            // --- BOTÃO DE MENU ---
            ImageButton menuButton = new ImageButton(context);
            menuButton.setImageResource(android.R.drawable.ic_menu_sort_by_size);
            menuButton.setBackgroundColor(Color.TRANSPARENT);
            menuButton.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            addView(menuButton);

            // --- TÍTULO CENTRAL ---
            TextView title = new TextView(context);
            title.setText(titleText);
            title.setTextColor(Color.WHITE);
            title.setTextSize(18);
            title.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            title.setLayoutParams(titleParams);
            addView(title);

            // --- BOTÃO DE PESQUISA ---
            ImageButton searchButton = new ImageButton(context);
            searchButton.setImageResource(android.R.drawable.ic_menu_search);
            searchButton.setBackgroundColor(Color.TRANSPARENT);
            searchButton.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            addView(searchButton);
        }
    }
}