package sg.edu.rp.c346.id21024223.mymovies;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> moviesList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        moviesList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);


        // Obtain the Android Version information based on the position
        Movie currentItem = moviesList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText("+" + currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "" + currentItem.getRating());
        if(currentItem.getRating().equals("G")){
            ivRating.setImageResource(R.drawable.rating_g);
        }else if(currentItem.getRating().equals("PG")){
            ivRating.setImageResource(R.drawable.rating_pg);
        }else if(currentItem.getRating().equals("PG13")){
            ivRating.setImageResource(R.drawable.rating_pg13);
        }else if(currentItem.getRating().equals("NC16")){
            ivRating.setImageResource(R.drawable.rating_nc16);
        }else if(currentItem.getRating().equals("M18")){
            ivRating.setImageResource(R.drawable.rating_m18);
        }else if(currentItem.getRating().equals("R21")){
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}