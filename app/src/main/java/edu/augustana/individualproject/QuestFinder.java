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

/**
 * This class is used to handle the activity for finding quests in Escape from Tarkov
 * by using a spinner that has every possible choice for the trader and then once a trader
 * is selected, the secondary spinner is updated with all possible quests for that trader
 * exclusively.
 */
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

    /**
     * On click to handle button pressing in this activity.
     * @param v - Parameter to handle the XML objects within this XML layout.
     */
    @Override
    public void onClick(View v) {
        if (v == submitButton) {
            Intent questViewer = new Intent(this, QuestViewer.class);
            questViewer.putExtra("trader", currentTrader);
            questViewer.putExtra("quest", currentQuest);
            startActivity(questViewer);
        }
    }

    /**
     * This method is used to populate the spinner for all possible traders and sends the selected
     * trader name to the getQuestsBasedOnTraderSelection method.
     */
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

    /**
     * This method gathers data from the database based on the selected trader in order to send the arraylist
     * of strings that are the names of all possible quests for the trader that was selected.
     * @param trader - String - Trader name
     */
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

    /**
     * This method is used to populate the spinner for all possible quests and will wait for the user to hit
     * submit to view the quest.
     */
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
