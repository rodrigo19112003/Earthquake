package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.earthquakeapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        EqAdapter adapter = new EqAdapter();
        binding.eqRecycler.setAdapter(adapter);

        /*for(Earthquake eq : eqList){
                Log.d("eq", eq.getMagnitude() + " " + eq.getPlace());
            }*/
        viewModel.getEqList().observe(this, adapter::submitList);
        viewModel.getEarthquakes();

        adapter.setOnItemClickListener(eq ->{
            Toast.makeText(MainActivity.this, eq.getMagnitude() + " " +  eq.getPlace(), Toast.LENGTH_SHORT).show();
        });
    }
}