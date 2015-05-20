package com.example.sachin.va;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Movie {
    private String name;
    private String id;
    private String year;
    private String genre;
    private String actors;
    private String director;

    private ArrayList<Scene> scenes;
    public Movie(String[] args) {
        if(args!=null && args.length == 6) {
            this.id = args[0];
            this.name = args[1];
            year = args[2];
            genre = args[3];
            director = args[4];
            actors = args[5];
        }
        else
        {
            name="ERR";
            year="";
            id="0";

        }
        this.scenes= new ArrayList<>();

    }
    public void addScene(Scene s)
    {
        scenes.add(s);
    }

    public ArrayList<Scene> getScenes() {
        return scenes;   }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }
}
