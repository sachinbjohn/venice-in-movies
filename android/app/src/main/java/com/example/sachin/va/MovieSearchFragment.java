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


 public class MovieSearchFragment extends Fragment implements SearchView.OnQueryTextListener {

     private SearchView mSearchView;
     private ListView mListView;
     MovieAdapter ma;

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         setRetainInstance(true);
         // TODO Auto-generated method stub

         View view = inflater.inflate(R.layout.movie_search_layout,container,false);

         ArrayList<Movie> movies=((MyApplication)getActivity().getApplication()).getAllMovies();
         mSearchView = (SearchView) view.findViewById(R.id.movie_search_view);
         mListView = (ListView) view.findViewById(R.id.movie_list_view);
        ma =new MovieAdapter(getActivity(), movies);
         mListView.setAdapter(ma);
         mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie m= (Movie)parent.getItemAtPosition(position);
                 Intent intent = new Intent(parent.getContext(), MovieDetails.class);
                 intent.putExtra("mid",m.getId());
                 startActivity(intent);
                 //Toast.makeText(getActivity(),m.getName()+"  "+m.getId(), Toast.LENGTH_SHORT).show();
             }
         });
         setupSearchView();
         return  view;
     }

     private void setupSearchView() {
         mSearchView.setIconifiedByDefault(false);
         mSearchView.setOnQueryTextListener(this);
         mSearchView.setSubmitButtonEnabled(true);
         mSearchView.setQueryHint("Search for movies here");
     }

     public boolean onQueryTextChange(String newText) {
         ma.getFilter().filter(newText);
         return true;
     }

     public boolean onQueryTextSubmit(String query) {
         return false;
     }
 }

