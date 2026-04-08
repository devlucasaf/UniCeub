import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class MediaQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 0, 0, 0);

        TextView infoText = new TextView(this);
        TextView adaptiveText = new TextView(this);
        adaptiveText.setText("Texto que se adapta ao tamanho da tela");

        layout.addView(infoText);
        layout.addView(adaptiveText);
        setContentView(layout);

        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        int screenWidthPx = metrics.widthPixels;
        int screenHeightPx = metrics.heightPixels;

        float density = metrics.density;

        float screenWidthDp = screenWidthPx / density;

        String orientation = (config.orientation == Configuration.ORIENTATION_LANDSCAPE) ? "Paisagem" : "Retrato";

        float fontScale = config.fontScale;

        infoText.setText(String.format(
                "Largura: %.0fpx (%.0fdp)\n" +
                "Altura: %dpx\n" +
                "Densidade: %.2f\n" +
                "Orientação: %s\n" +
                "Escala de fonte: %.1f",
                screenWidthPx, screenWidthDp, screenHeightPx, density, orientation, fontScale
        ));

        int horizontalMarginDp;
        if (screenWidthDp >= 600) {
            horizontalMarginDp = 32;
        } 
        
        else {
            horizontalMarginDp = 16;
        }
        int marginPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                horizontalMarginDp,
                metrics
        );

        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) adaptiveText.getLayoutParams();
        if (textParams == null) {
            textParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
        textParams.setMargins(marginPx, marginPx, marginPx, marginPx);
        adaptiveText.setLayoutParams(textParams);

        float baseTextSizeSp = 14f;
        float finalSizePx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                baseTextSizeSp,
                metrics
        ) * fontScale; 
        adaptiveText.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalSizePx);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recreate(); 
    }
}