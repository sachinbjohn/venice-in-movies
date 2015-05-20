package com.example.sachin.va;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class MyApplication extends Application {
    private static MyApplication sInstance;

    private HashMap<String,Movie> movieTable;
    private HashMap<String,MovieLocation> locTable;
    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();
    }

    protected void initializeInstance() {
        movieTable = new HashMap<>();
        locTable = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.moviestable)));
        CSVReader cr  = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.moviestable)));
        int line=0;
        try {
        for(String[] args: cr.readAll())
        {
            line++;
            if(args!=null && args.length==6) {
                Movie m = new Movie(args);
                movieTable.put(m.getId(), m);
            }else
                Log.e("Movie", " line="+line);
        }

        cr = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.locationtable)));
            line=0;
            for(String[] args: cr.readAll())
            {
                line++;
                if(args!=null && args.length==4) {
                MovieLocation l = new MovieLocation(args);
                locTable.put(l.getId(),l);
            }else
            Log.e("Location", " line="+line);
            }

            cr = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.movielocationstable)));
            line=0;
            for(String[] args: cr.readAll())

            {
                line++;
                Movie m = movieTable.get(args[0]);
                MovieLocation l = locTable.get(args[1]);
                if(m!=null && l!=null)
                {

                    Scene s=new Scene(l,m,args[2]);
                    m.addScene(s);
                    l.addScene(s);
                }
                else
                    Log.e("MovieLocation", " line="+line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Movie getMovie(String id)
    {
        return movieTable.get(id);
    }
    public ArrayList<Movie> getAllMovies()
    {
        return new ArrayList<>(movieTable.values());
    }

    public MovieLocation getLoc(String id)
    {
        return locTable.get(id);
    }
    public ArrayList<MovieLocation> getAllLocs()
    {
        return new ArrayList<>(locTable.values());
    }
        // do all your initialization here

    }

