package edu.augustana.individualproject;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class QuestFinder extends AppCompatActivity implements View.OnClickListener {

    String[] traders = {"Prapor", "Therapist", "Skier", "Peacekeeper", "Mechanic", "Ragman", "Jaeger", "Fence"};
    Button submitButton;
    Spinner traderSpinner, questSpinner;
    Adapter tAdapter, qAdapter;
    ArrayList<String> questNameList;
    ArrayList<Quest> questArrayList;
    String currentTrader, currentQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_finder);
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
            questViewer.putExtra("trader", currentTrader);
            questViewer.putExtra("quest", currentQuest);
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
                getQuestsBasedOnTraderSelection(traders[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getQuestsBasedOnTraderSelection(String trader) {
        currentTrader = trader;
        DatabaseReference quests = FirebaseDatabase.getInstance().getReference("quests").child(trader);
        quests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questNameList.clear();
                questArrayList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Quest newQuest = snapshot.getValue(Quest.class);
                    questArrayList.add(newQuest);
                }
                for(Quest q: questArrayList) {
                    questNameList.add(q.getQuestName());
                }
                setupQuestSpinner(questNameList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setupQuestSpinner(final ArrayList<String> questArrayList) {
        questSpinner = findViewById(R.id.questSpinner);
        qAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.traderlayout, questNameList);
        questSpinner.setAdapter((SpinnerAdapter) qAdapter);
        questSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentQuest = questArrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
