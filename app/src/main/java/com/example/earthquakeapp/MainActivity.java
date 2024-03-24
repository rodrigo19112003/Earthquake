package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.earthquakeapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this,
                new MainViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));
        EqAdapter adapter = new EqAdapter();
        adapter.setOnItemClickListener(earthquake ->
                openDeatilActivity(earthquake.getId(), earthquake.getPlace(), earthquake.getMagnitude(),
                        earthquake.getTime(), earthquake.getLongitude(), earthquake.getLatitude()));
        binding.eqRecycler.setAdapter(adapter);
        viewModel.downloadEarthquakes();
        viewModel.getEqList().observe(this,eqList ->{
            adapter.submitList(eqList);
        });

    }

    private void openDeatilActivity(String id, String place, double magnitude, long time,
                                    double longitude, double latitude){
        Earthquake earthquake = new Earthquake(id, place, magnitude, time, longitude, latitude);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EARTHQUAKE_KEY, earthquake);
        startActivity(intent);
    }
}