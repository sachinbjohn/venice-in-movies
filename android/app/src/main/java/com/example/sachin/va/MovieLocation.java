package com.example.sachin.va;

import android.location.Location;
import android.location.LocationProvider;

import java.util.ArrayList;
import java.util.Collections;

public class MovieLocation {
    private String name;
    private String id;
    private String lat;
    private String lng;
    private ArrayList<Scene> scenes;
    private ArrayList<Movie> movies;
    public MovieLocation(String[] args) {

        if(args !=null && args.length ==4) {
            id = args[0];
            name = args[1];
            lat = args[2];
            lng = args[3];
        }
        else
        {
            name = "ere";
            id="0";
        }
        scenes = new ArrayList<>();
        movies = new ArrayList<>();

    }

    public Double getLat() {
        return new Double(lat);
    }

    public Double getLng() {
        return new Double(lng);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        if(name.contains(", Venice"))
        return name.substring(0,name.indexOf(", Venice"));
        else
        return name;
    }
    public void addScene(Scene s)
    {
        scenes.add(s);

        movies.add(s.m);
    }
    public ArrayList<Scene> getScenes() {
        return scenes;
    }
    public ArrayList<Movie> getMovies() { return new ArrayList<>(movies); }




}
