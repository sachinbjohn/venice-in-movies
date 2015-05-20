package com.example.sachin.va;

import android.app.Activity;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter implements Filterable {
    private ArrayList<MovieLocation> allLocs;
    private ArrayList<MovieLocation> filteredLocs;
    private LayoutInflater inflator;

    public LocationAdapter(Activity context, ArrayList<MovieLocation> allLocs) {
        this.allLocs=allLocs;
        this.filteredLocs=allLocs;
        inflator=context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return filteredLocs.size();
    }

    @Override
    public MovieLocation getItem(int position) {
        return filteredLocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieLocation l=filteredLocs.get(position);
        TextView view = null;

        if(convertView==null)
        {
            view = (TextView)inflator.inflate(android.R.layout.simple_list_item_1, null);
        }
        else
        {
            view =(TextView)convertView;
        }
        view.setText(l.getName());
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
                    ArrayList<MovieLocation> temp = new ArrayList<>();
                    for (int i = 0; i < allLocs.size(); i++) {
                        MovieLocation l= allLocs.get(i);
                        if (l.getName().toLowerCase().contains(constraint))
                            temp.add(l);
                    }
                    result.values=temp;
                    result.count=temp.size();
                } else {
                    synchronized (this) {
                        result.count = allLocs.size();
                        result.values = allLocs;
                    }
                }
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredLocs=(ArrayList<MovieLocation>)results.values;
                notifyDataSetChanged();

            }
        };
    }
}
