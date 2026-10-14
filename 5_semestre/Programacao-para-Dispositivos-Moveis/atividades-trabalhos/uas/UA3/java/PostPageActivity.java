import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

public class PostPageActivity extends AppCompatActivity {
    ImageView bannerImage;    
    ImageView iconImage;      
    TextView  titleText ;     
    TextView  dateText;     
    TextView  descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post de Notícia");

        bannerImage     = findViewById(R.id.bannerImage);
        iconImage       = findViewById(R.id.iconImage);
        titleText       = findViewById(R.id.titleText);
        dateText        = findViewById(R.id.dateText);
        descriptionText = findViewById(R.id.descriptionText);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1518770660439-4636190af475")
                .centerCrop()
                .into(bannerImage);

        Glide.with(this)
                .load("https://cdn-icons-png.flaticon.com/512/732/732212.png")
                .into(iconImage);
    }
}