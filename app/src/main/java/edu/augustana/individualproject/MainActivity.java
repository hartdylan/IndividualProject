package edu.augustana.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.individualproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button questBtn, hideoutItemBtn, aboutBtn, modifyDBBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main menu");

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
        Intent intent = new Intent();
        if (v == questBtn) {
            intent = new Intent(this, QuestFinder.class);
        } else if (v == hideoutItemBtn) {
            intent = new Intent(this, HideoutActivity.class);
        } else if (v == aboutBtn) {
            intent = new Intent(this, AboutActivity.class);
        } else if (v == modifyDBBtn) {
            intent = new Intent(this, ModifyDB.class);
        }
        startActivity(intent);
    }

}
