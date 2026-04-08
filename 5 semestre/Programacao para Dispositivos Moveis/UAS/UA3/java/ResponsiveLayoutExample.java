import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ResponsiveLayoutExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int larguraDp = (int) (getScreenWidthPixels() / getResources().getDisplayMetrics().density);
        boolean isMobile = larguraDp < 600;

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Exemplo de Layout Flutter");
        setSupportActionBar(toolbar);

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setOrientation(isMobile ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);

        if (isMobile) {
            contentLayout.addView(headerView());
            contentLayout.addView(spaceView(20, false)); 
            contentLayout.addView(contentView(), new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
            contentLayout.addView(spaceView(20, false));
            contentLayout.addView(footerView());
        } 
        
        else {
            contentLayout.addView(contentView(), new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            contentLayout.addView(spaceView(20, true)); 

            LinearLayout rightColumn = new LinearLayout(this);
            rightColumn.setOrientation(LinearLayout.VERTICAL);
            rightColumn.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));

            rightColumn.addView(headerView());
            rightColumn.addView(spaceView(20, false));
            rightColumn.addView(footerView());

            contentLayout.addView(rightColumn);
        }

        rootLayout.addView(toolbar);
        rootLayout.addView(contentLayout);
        setContentView(rootLayout);
    }

    private int getScreenWidthPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private View spaceView(int sizeDp, boolean horizontal) {
        View space = new View(this);
        int px = dpToPx(sizeDp);
        if (horizontal) {
            space.setLayoutParams(new LinearLayout.LayoutParams(px, ViewGroup.LayoutParams.MATCH_PARENT));
        } 
        
        else {
            space.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px));
        }
        space.setBackgroundColor(Color.TRANSPARENT);
        return space;
    }

    private TextView headerView() {
        TextView header = new TextView(this);
        header.setText("Cabeçalho");
        header.setTextColor(Color.WHITE);
        header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        header.setBackgroundColor(Color.BLUE);
        header.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
        header.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return header;
    }

    private TextView contentView() {
        TextView content = new TextView(this);
        content.setText("Conteúdo Principal");
        content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        content.setGravity(Gravity.CENTER);
        content.setBackgroundColor(Color.LTGRAY);
        content.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return content;
    }

    private TextView footerView() {
        TextView footer = new TextView(this);
        footer.setText("Rodapé");
        footer.setTextColor(Color.WHITE);
        footer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        footer.setBackgroundColor(Color.BLACK);
        footer.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
        footer.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return footer;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recreate();
    }
}