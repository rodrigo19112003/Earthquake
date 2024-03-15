package com.example.earthquakeapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.earthquakeapp.Response.EarthquakeJSONResponse;
import com.example.earthquakeapp.pojos.Feature;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Earthquake>> eqList = new MutableLiveData<>();

    public LiveData<List<Earthquake>> getEqList(){
        return eqList;
    }
    private MainRepository repository = new MainRepository();
    public void getEarthquakes(){
        repository.getEarthquakes(earthquakeList -> {
            eqList.setValue(earthquakeList);
        });
    }
}
