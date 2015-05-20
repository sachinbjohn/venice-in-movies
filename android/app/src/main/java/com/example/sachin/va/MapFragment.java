package com.example.sachin.va;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapFragment extends SupportMapFragment {

    GoogleMap mapView;
    static String locID;
    private Context context;

    @Override
    public void onCreate(Bundle arg0) {
            super.onCreate(arg0);
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup arg1,
                    Bundle arg2) {
            View view = super.onCreateView(mInflater, arg1, arg2);
              setMapTransparent((ViewGroup) view);

              return view;
    }
    private void setUpMap() {
        // For showing a move to my loction button
        mapView.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        Double latitude, longitude;

        latitude=45.4;
        longitude=12.3;
        float zoom=12.0f;
        final MyApplication app = (MyApplication)getActivity().getApplication();
        for(MovieLocation l:app.getAllLocs()) {
            boolean show = l.getId().equals(locID);
            Marker m=mapView.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLng())).title(l.getName()).snippet(l.getId()));
            if(show)
            {
                latitude=l.getLat();
                m.showInfoWindow();
                longitude=l.getLng();
                zoom=16.0f;
            }
            // For zooming automatically to the Dropped PIN Location
        }
        mapView.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                MovieLocation l = app.getLoc(marker.getSnippet());
                View v = getActivity().getLayoutInflater().inflate(R.layout.info_window, null);

                TextView n= (TextView)v.findViewById(R.id.info_window_title);
                n.setText(l.getName());
                return v;
            }
        });
        mapView.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent i=new Intent(getActivity(),LocationDetails.class);
                String lid = marker.getSnippet();
                i.putExtra("lid", lid);
                startActivity(i);
            }
        });
        mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), zoom));
    }
    private void setMapTransparent(ViewGroup group) {
            int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
            View child = group.getChildAt(i);

                    if (child instanceof ViewGroup) {
                        setMapTransparent((ViewGroup) child);
                    } else if (child instanceof SurfaceView) {
                        child.setBackgroundColor(0x00000000);
                    }
                }
            }

    @Override
    public void onInflate(Activity arg0, AttributeSet arg1, Bundle arg2) {
            super.onInflate(arg0, arg1, arg2);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            context = getActivity();
            mapView = getMap();

            setUpMap();
            }
}