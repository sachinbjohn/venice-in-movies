package com.example.sachin.va;


public class Scene {

    MovieLocation loc;
    Movie m;
    String desc;
    String time;
    String link;

    public Scene(MovieLocation loc, Movie m,String line) {
        this.loc = loc;
        this.m = m;
        this.desc = line;
    }

    public String getDesc() {
        return desc;
    }
    public  String toString()
    {
        return loc.getName()+"\n"+getDesc();
    }
}
