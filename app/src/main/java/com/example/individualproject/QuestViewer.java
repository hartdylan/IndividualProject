package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestViewer extends AppCompatActivity {

    TextView traderText, questText, lvlReqText;
    ImageView traderImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_viewer);
        getSupportActionBar().setTitle("Quest Viewer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        traderImg = findViewById(R.id.traderImg);
        traderText = findViewById(R.id.traderText);
        questText = findViewById(R.id.questNameText);
        lvlReqText = findViewById(R.id.lvlReqText);
        traderImg.setImageResource(R.drawable.prapor);
    }
}
