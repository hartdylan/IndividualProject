package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HideoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hideout);
        getSupportActionBar().setTitle("Hideout upgrade finder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
