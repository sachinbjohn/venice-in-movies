package com.example.sachin.va;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class LocationDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        String lid = getIntent().getStringExtra("lid");
        final MovieLocation l =((MyApplication)getApplication()).getLoc(lid);


        if(l!=null) {
            TextView lname = (TextView) findViewById(R.id.locName);
            lname.setText(l.getName());


            TextView lcord = (TextView) findViewById(R.id.coordinates);
            lcord.setText(l.getLat() + ", " + l.getLng());

            ListView mListView = (ListView) findViewById(R.id.loc_movie_list_view);
            int s = l.getMovies().size();
            Log.e("Location details", "Size=" + s);
            mListView.setAdapter(new MovieAdapter(this, l.getMovies()));
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie m = (Movie) parent.getItemAtPosition(position);
                    Intent intent = new Intent(parent.getContext(), MovieDetails.class);
                    intent.putExtra("mid", m.getId());
                    startActivity(intent);
                }
            });

            Button b= (Button)findViewById(R.id.viewmapbutton);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Intent i=new Intent(v.getContext(),MainActivity.class);
                    i.putExtra("lid",l.getId());
                    startActivity(i);
                }
            });
        }
        else
            Log.e("Location details", lid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_details, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
