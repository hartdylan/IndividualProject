package com.example.individualproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class QuestActivity extends AppCompatActivity implements View.OnClickListener {

    String[] traders = {"Prapor", "Therapist", "Skier", "Peacekeeper", "Mechanic", "Ragman", "Jaeger", "Fence"};
    Button submitButton;
    Spinner traderSpinner, questSpinner;
    Adapter tAdapter, qAdapter;
    ArrayList<String> questNameList;
    ArrayList<Quest> questArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        getSupportActionBar().setTitle("Quest finder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        questArrayList = new ArrayList<Quest>();
        questNameList = new ArrayList<String>();
        setupTraderSpinner();
    }

    @Override
    public void onClick(View v) {
        if (v == submitButton) {
            Intent questViewer = new Intent(this, QuestViewer.class);
            startActivity(questViewer);
        }
    }

    public void setupTraderSpinner() {
        traderSpinner = findViewById(R.id.traderSpinner);
        tAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.traderlayout, traders);
        traderSpinner.setAdapter((SpinnerAdapter) tAdapter);
        traderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getQuestsBasedOnSelection(traders[position]);
//                Toast.makeText(QuestActivity.this, ""+questArrayList.size(), Toast.LENGTH_SHORT).show();
//                setupQuestSpinner(questNameList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getQuestsBasedOnSelection(String trader) {
        questArrayList.clear();
        DatabaseReference quests = FirebaseDatabase.getInstance().getReference("quests");
        DatabaseReference traderRef = quests.child(trader);
        traderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Quest newQuest = snapshot.getValue(Quest.class);
                    questArrayList.add(newQuest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setupQuestSpinner(ArrayList<String> questArrayList) {
        questSpinner = findViewById(R.id.questSpinner);
        qAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.traderlayout, questNameList);
        questSpinner.setAdapter((SpinnerAdapter) qAdapter);
        questSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
