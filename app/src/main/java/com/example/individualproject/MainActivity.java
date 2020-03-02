package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button questBtn, questItemBtn, hideoutItemBtn, aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questBtn = findViewById(R.id.questBtn);
        questItemBtn = findViewById(R.id.questItemBtn);
        hideoutItemBtn = findViewById(R.id.hideoutItemBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        setOnClickListeners();
    }

    public void setOnClickListeners() {
        questBtn.setOnClickListener(this);
        questItemBtn.setOnClickListener(this);
        hideoutItemBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if (v == questBtn) {
                // go to quest activity
            } else if (v == questItemBtn) {
                // go to quest item activity
            } else if (v == hideoutItemBtn) {
                // go to hideout item activity
            } else if (v == aboutBtn) {
                // go to about / credits activity
            }
    }
}
