package edu.augustana.individualproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class is used to handle the activity within the application that displays information
 * about the application itself and the inspiration behind it. There is nothing really complicated
 * about this code whatsoever.
 */

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
