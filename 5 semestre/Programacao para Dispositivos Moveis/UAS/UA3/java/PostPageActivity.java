import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

public class PostPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post de Notícia");

        ImageView bannerImage = findViewById(R.id.bannerImage);
        ImageView iconImage = findViewById(R.id.iconImage);
        TextView titleText = findViewById(R.id.titleText);
        TextView dateText = findViewById(R.id.dateText);
        TextView descriptionText = findViewById(R.id.descriptionText);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1518770660439-4636190af475")
                .centerCrop()
                .into(bannerImage);

        Glide.with(this)
                .load("https://cdn-icons-png.flaticon.com/512/732/732212.png")
                .into(iconImage);
    }
}