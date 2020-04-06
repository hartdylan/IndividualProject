package edu.augustana.individualproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class is used to display the quest information to the user in the QuestViewer activity
 */
public class QuestViewer extends AppCompatActivity {

    TextView traderText, questText, lvlReqText;
    ImageView traderImg;
    int drawableId;
    String trader, quest;
    ListView objListView, itemListView, rewListView;
    ArrayAdapter<String> objAd, itemAd, rewAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_viewer);
        getSupportActionBar().setTitle("Quest Viewer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeViewIDs();
        setupQuest();
    }

    /**
     * Get references to all of the XML objects that are to be used in the activity.
     */
    public void initializeViewIDs() {
        traderImg = findViewById(R.id.traderImg);
        traderText = findViewById(R.id.traderNameText);
        questText = findViewById(R.id.questNameText);
        lvlReqText = findViewById(R.id.lvlReqText);
        objListView = findViewById(R.id.objectivesListView);
        itemListView = findViewById(R.id.questItemListView);
        rewListView = findViewById(R.id.rewardsListView);
    }

    /**
     * This method is used to get the proper data from the database for the select quest before sending the data to
     * the setupListViews method.
     */
    public void setupQuest() {
        trader = getIntent().getStringExtra("trader");
        quest = getIntent().getStringExtra("quest");
        Resources r = getResources();
        drawableId = r.getIdentifier(trader.toLowerCase().replaceAll(" ", ""), "drawable", "com.example.individualproject");
        traderImg.setImageResource(drawableId);
        DatabaseReference quests = FirebaseDatabase.getInstance().getReference("quests").child(trader).child(quest);
        quests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Quest quest = dataSnapshot.getValue(Quest.class);
                setupListViews(quest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method is used to populate the scrollable list views with the array list data from the quest object.
     * @param q
     */
    public void setupListViews(Quest q) {
        traderText.setText(q.getTrader());
        questText.setText(q.getQuestName());
        lvlReqText.setText(String.valueOf(q.getLvlRequirement()));
        objAd = new ArrayAdapter<String>(QuestViewer.this, R.layout.listlayout, q.getObjectives());
        objListView.setAdapter(objAd);
        itemAd = new ArrayAdapter<String>(QuestViewer.this, R.layout.listlayout, q.getQuestItems());
        itemListView.setAdapter(itemAd);
        rewAd = new ArrayAdapter<String>(QuestViewer.this, R.layout.listlayout, q.getRewards());
        rewListView.setAdapter(rewAd);
    }
}
