package com.example.sachin.va;

import android.app.Activity;
import android.test.mock.MockDialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieAdapter extends BaseAdapter implements Filterable {
    private ArrayList<Movie> allMovies;
    private ArrayList<Movie> filteredMovies;
    private LayoutInflater inflator;

    public MovieAdapter(Activity context, ArrayList<Movie> allMovies) {
        this.allMovies = allMovies;
        this.filteredMovies =allMovies;
        inflator=context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return filteredMovies.size();
    }

    @Override
    public Movie getItem(int position) {
        return filteredMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie m=filteredMovies.get(position);
        TextView view = null;

        if(convertView==null)
        {
            view = (TextView)inflator.inflate(android.R.layout.simple_list_item_1, null);
        }
        else
        {
            view =(TextView)convertView;
        }
        view.setText(m.getName()+" ("+m.getYear()+")");
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();
                if (constraint != null && constraint.toString().length() > 0) {
                    ArrayList<Movie> temp = new ArrayList<>();
                    for (int i = 0; i < allMovies.size(); i++) {
                        Movie m = allMovies.get(i);
                        if (m.getName().toLowerCase().contains(constraint))
                            temp.add(m);
                    }
                    result.values=temp;
                    result.count=temp.size();
                } else {
                    synchronized (this) {
                        result.count = allMovies.size();
                        result.values = allMovies;
                    }
                }
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredMovies=(ArrayList<Movie>)results.values;
                notifyDataSetChanged();

            }
        };
    }
}
