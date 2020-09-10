package com.example.android.quakereport;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;

    public Earthquake(Double magnitude,String Location,Long timeInMilliseconds)
    {
        mMagnitude = magnitude;
        mLocation = Location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public Double getMagnitude()
    {
        return mMagnitude;
    }

    public String getLocation()
    {
        return mLocation;
    }
    public Long getTimeInMilliseconds()
    {
        return mTimeInMilliseconds;
    }

}
