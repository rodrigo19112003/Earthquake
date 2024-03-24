package com.example.earthquakeapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "earthquakes")
public class Earthquake implements Parcelable {

    @PrimaryKey
    @NonNull
    private String id;
    private String place;
    private double magnitude;
    private long time;
    private double longitude;
    private double latitude;

    public Earthquake(String id, String place, double magnitude, long time, double longitude, double latitude) {
        this.id = id;
        this.place = place;
        this.magnitude = magnitude;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    protected Earthquake(Parcel in) {
        id = in.readString();
        place = in.readString();
        magnitude = in.readDouble();
        time = in.readLong();
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(place);
        dest.writeDouble(magnitude);
        dest.writeLong(time);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }
}
