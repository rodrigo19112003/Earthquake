package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.earthquakeapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    public static final String EARTHQUAKE_KEY = "earthquake_key";
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        Earthquake earthquake = extras.getParcelable(EARTHQUAKE_KEY);

        binding.setEarthquake(earthquake);

        binding.btnGoBack.setOnClickListener(v ->{
            finish();
        });
    }
}