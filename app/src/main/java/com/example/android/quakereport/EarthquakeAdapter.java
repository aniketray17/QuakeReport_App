package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    String primaryLocation;
    String locationOffset;
    private static final String LOCATION_SEPARATOR = "of";
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);
        //find the textview with view id magnitude
        TextView magnitudeView = (TextView)listItemView.findViewById(R.id.magnitude);
        //Receive the formatted magnitude upto one decimal place
        String formattedMagnitude = formatMag(currentEarthquake.getMagnitude());
        //display the magnitude of the currrent earthquake in the textview
        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquake.getLocation();

        //create a new date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        //find the textview with view id date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        //format the date string(i.e "Mar 3, 1984");
        String formattedDate = formatDate(dateObject);
        //display the date of current earthquake in the textview
        dateView.setText(formattedDate);

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);
        //find the textview with view id time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        //format the timeString
        String formattedTime = formatTime(dateObject);
        //display the time of current earthquake in the textview
        timeView.setText(formattedTime);

        //return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string from a date object
     */
    private String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formated time string from a date object
     */
    private String formatTime(Date dateObject)
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    /**
     * Return the formatted magnitude
     */
    private String formatMag(Double mag)
    {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}