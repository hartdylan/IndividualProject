package edu.augustana.individualproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // FIELDS
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

    /**
     * Setup on click listeners for buttons on the main activity.
     */
    public void setOnClickListeners() {
        questBtn.setOnClickListener(this);
        hideoutItemBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        modifyDBBtn.setOnClickListener(this);
    }

    /**
     * Handles button clicks in the main activity.
     * @param v - Handles the button clicks with respect to the actual XML objects.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == questBtn) {
            intent = new Intent(this, QuestFinder.class);
        } else if (v == hideoutItemBtn) {
            intent = new Intent(this, HideoutUpgradeFinder.class);
        } else if (v == aboutBtn) {
            intent = new Intent(this, About.class);
        }
        startActivity(intent);
    }

}
