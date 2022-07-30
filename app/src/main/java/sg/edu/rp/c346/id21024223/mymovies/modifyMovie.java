package sg.edu.rp.c346.id21024223.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class modifyMovie extends AppCompatActivity {

    Button update, delete, cancel;
    EditText etUpdateMovieTitle, etUpdateGenre, etUpdateYear;
    RadioGroup updateRating;
    RadioButton updateStar1, updateStar2, updateStar3, updateStar4, updateStar5;
    android.graphics.Movie movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_movie);

        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        cancel = findViewById(R.id.btnCancel);
        etUpdateMovieTitle = findViewById(R.id.etUpdateMovieTitle);
        etUpdateGenre = findViewById(R.id.etUpdateGenre);
        etUpdateYear = findViewById(R.id.etUpdateYear);
        updateRating = findViewById(R.id.etRatingUpdate);
        updateStar1 = findViewById(R.id.updateStar1);
        updateStar2 = findViewById(R.id.updateStar2);
        updateStar3 = findViewById(R.id.updateStar3);
        updateStar4 = findViewById(R.id.updateStar4);
        updateStar5 = findViewById(R.id.updateStar5);

        Intent i = getIntent();
        movieDetails = (Movie) i.getSerializableExtra("movie");

        etUpdateMovieTitle.setText(movieDetails.getTitle());
        etUpdateGenre.setText(movieDetails.getGenre());
        etUpdateYear.setText(movieDetails.getYear() + "");
        int starCount = 0;
        if (movieDetails.getStar() == 1) {
            updateStar1.setChecked(true);
        } else if (movieDetails.getStar() == 2) {
            updateStar2.setChecked(true);
        } else if (movieDetails.getStar() == 3) {
            updateStar3.setChecked(true);
        } else if (movieDetails.getStar() == 4) {
            updateStar4.setChecked(true);
        } else if (movieDetails.getStar() == 5) {
            updateStar5.setChecked(true);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(modifyMovie.this);
                int id = movieDetails.getId();
                String newTitle = etUpdateMovieTitle.getText().toString();
                String newSinger = etUpdateGenre.getText().toString();
                String newYear = etUpdateYear.getText().toString();
                int newYearToInt = Integer.parseInt(newYear);

                int checkedRadioId = updateRating.getCheckedRadioButtonId();
                int newRating = 0;

                if (checkedRadioId == R.id.updateStar1) {
                    newRating = 1;
                } else if (checkedRadioId == R.id.updateStar2) {
                    newRating = 2;
                } else if (checkedRadioId == R.id.updateStar3) {
                    newRating = 3;
                } else if (checkedRadioId == R.id.updateStar4) {
                    newRating = 4;
                } else if (checkedRadioId == R.id.updateStar5) {
                    newRating = 5;
                }

                movieDetails.setMovieContent(id, newTitle, newSinger, newYearToInt, newRating);
                dbh.updateMovie(movieDetails);
                dbh.close();
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(modifyMovie.this);
                dbh.deleteSong(movieDetails.getID());
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
