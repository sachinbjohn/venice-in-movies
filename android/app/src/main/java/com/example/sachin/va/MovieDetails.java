package com.example.sachin.va;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MovieDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String mid = getIntent().getStringExtra("mid");
        Movie m =((MyApplication)getApplication()).getMovie(mid);

        TextView mname= (TextView) findViewById(R.id.movieName);
        mname.setText(m.getName()+ " ("+m.getYear()+")");

        TextView mgenre= (TextView) findViewById(R.id.movieGenre);
        mgenre.setText(m.getGenre());

        TextView mdir= (TextView) findViewById(R.id.movieDirector);
        mdir.setText(m.getDirector());

        TextView mact= (TextView) findViewById(R.id.movieActor);
        mact.setText(m.getActors());

        TextView mimdb= (TextView) findViewById(R.id.movieIMDB);
        mimdb.setClickable(true);
        mimdb.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.imdb.com/title/"+m.getId()+"'> View on IMDb</a>";
        mimdb.setText(Html.fromHtml(text));

        ListView mListView=(ListView) findViewById(R.id.movie_loc_list_view);
         mListView.setAdapter(new ArrayAdapter<Scene>(this,android.R.layout.simple_list_item_1,m.getScenes()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieLocation l = ((Scene) parent.getItemAtPosition(position)).loc;
                Intent intent = new Intent(parent.getContext(), LocationDetails.class);
                intent.putExtra("lid",l.getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
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
