package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button questBtn, hideoutItemBtn, aboutBtn, modifyDBBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questBtn = findViewById(R.id.questBtn);
        hideoutItemBtn = findViewById(R.id.hideoutItemBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        modifyDBBtn = findViewById(R.id.modifyDBBtn);
        setOnClickListeners();
    }

    public void setOnClickListeners() {
        questBtn.setOnClickListener(this);
        hideoutItemBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        modifyDBBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if (v == questBtn) {
                Intent questIntent = new Intent(this, QuestActivity.class);
                startActivity(questIntent);
            }  else if (v == hideoutItemBtn) {
                // go to hideout item activity
            } else if (v == aboutBtn) {
                // go to about / credits activity
            } else if (v == modifyDBBtn) {
                Intent modifyDBIntent = new Intent(this, ModifyDB.class);
                startActivity(modifyDBIntent);
            }
    }

}
