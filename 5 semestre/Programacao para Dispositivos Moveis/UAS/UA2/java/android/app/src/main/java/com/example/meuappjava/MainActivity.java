package com.example.meuappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meuappjava.network.ApiService;
import com.example.meuappjava.network.Post;
import com.example.meuappjava.network.RetrofitClient;
import com.example.meuappjava.utils.ToastHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFetch = findViewById(R.id.btnFetchData);
        Button btnNext = findViewById(R.id.btnNextActivity);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPostFromApi();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchPostFromApi() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Post> call = apiService.getPost(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Post post = response.body();
                    ToastHelper.showLongToast(MainActivity.this,
                            "Título: " + post.getTitle());
                } 
                
                else {
                    ToastHelper.showShortToast(MainActivity.this, "Erro na resposta");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                ToastHelper.showShortToast(MainActivity.this, "Falha: " + t.getMessage());
            }
        });
    }
}
