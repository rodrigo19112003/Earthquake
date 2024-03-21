package com.example.earthquakeapp;

import androidx.lifecycle.LiveData;

import com.example.earthquakeapp.Response.EarthquakeJSONResponse;
import com.example.earthquakeapp.database.EqDatabase;
import com.example.earthquakeapp.pojos.Feature;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainRepository {
    private final EqDatabase database;
    public MainRepository(EqDatabase database) {
        this.database = database;
    }
    public LiveData<List<Earthquake>> getEqList() {
        return database.eqDAO().getEarthquakes();
    }
    public void downloadAndSaveEarthquakes() {
        ApiClient.Service service = ApiClient.getInstance().getService();
        service.getEarthquakes().enqueue(new Callback<EarthquakeJSONResponse>() {
            @Override
            public void onResponse(Call<EarthquakeJSONResponse> call,
                                   Response<EarthquakeJSONResponse> response) {
                List<Earthquake> earthquakeList = getEarthquakesWithMoshi(response.body());
                EqDatabase.databaseWriteExecutor.execute(() -> {
                    database.eqDAO().insertAll(earthquakeList);
                });
            }
            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {
            }
        });
    }
    private List<Earthquake> getEarthquakesWithMoshi(EarthquakeJSONResponse body) {
        ArrayList<Earthquake> eqList = new ArrayList<>();
        List<Feature> features = body.getFeatures();
        for (Feature feature: features) {
            String id = feature.getId();
            double magnitude = feature.getProperties().getMagnitude();
            String place = feature.getProperties().getPlace();
            long time = feature.getProperties().getTime();
            double longitude = feature.getGeometry().getLongitude();
            double latitude = feature.getGeometry().getLatitude();
            Earthquake earthquake = new Earthquake(id, place, magnitude, time,
                    longitude, latitude);
            eqList.add(earthquake);
        }
        return eqList;
    }
}
