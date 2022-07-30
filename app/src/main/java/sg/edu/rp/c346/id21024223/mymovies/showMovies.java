package sg.edu.rp.c346.id21024223.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;


import java.util.ArrayList;

public class showMovies extends AppCompatActivity {

    ToggleButton allMovies;
    Spinner yearSelection;
    ArrayList<Movie> al;
    ListView lv;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lv = findViewById(R.id.lv);

        al = new ArrayList<Movie>();
        lv.setAdapter(adapter);

        DBHelper dbh = new DBHelper(showMovies.this);
        al.clear();
        al.addAll(dbh.getAllMovies());
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie movie = al.get(position);
                Intent i = new Intent(showMovies.this,
                        modifyMovie.class);
                startActivity(i);
            }
        });

        /*
        yearSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        al.clear();
                        al.addAll(dbh.getAllSongs());
                        adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
         */


    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(showMovies.this);
        al.clear();
        al.addAll(dbh.getAllMovies());
        adapter.notifyDataSetChanged();
    }

}