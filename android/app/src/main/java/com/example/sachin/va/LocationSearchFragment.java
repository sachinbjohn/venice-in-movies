package com.example.sachin.va;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class LocationSearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private ListView mListView;
    private LocationAdapter la;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.location_search_layout,container,false);

        ArrayList<MovieLocation> locs=((MyApplication)getActivity().getApplication()).getAllLocs();

        mSearchView = (SearchView) view.findViewById(R.id.location_search_view);
        mListView = (ListView) view.findViewById(R.id.location_list_view);
        la =new LocationAdapter(getActivity(), locs);
        mListView.setAdapter(la);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieLocation l = (MovieLocation) parent.getItemAtPosition(position);
                Intent intent = new Intent(parent.getContext(), LocationDetails.class);
                intent.putExtra("lid",l.getId());
                startActivity(intent);
            }
        });
        setupSearchView();
        return  view;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search for locations here");
    }

    public boolean onQueryTextChange(String newText) {
        la.getFilter().filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}