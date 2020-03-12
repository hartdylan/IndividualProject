package edu.augustana.individualproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ModifyDB extends AppCompatActivity implements View.OnClickListener {

    Button addButton, addObj, addRew, addItem;
    DatabaseReference quests = FirebaseDatabase.getInstance().getReference("quests");
    ArrayList<String> objList, rewList, itemList;
    EditText questName, traderName, lvl, objective, reward, item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydb);
        getSupportActionBar().setTitle("Modify database");
        addButton = findViewById(R.id.button);
        addObj = findViewById(R.id.addObj);
        addRew = findViewById(R.id.addRew);
        addItem = findViewById(R.id.addItem);
        objective = findViewById(R.id.objectivesText);
        reward = findViewById(R.id.rewardsText);
        item = findViewById(R.id.questItemText);
        questName = findViewById(R.id.questName);
        traderName = findViewById(R.id.traderText);
        lvl = findViewById(R.id.lvlReqText);
        setOnClickListeners();
        makeObjectArrays();
    }

    @Override
    public void onClick(View v) {
        if (v == addButton) {
            Toast.makeText(this, "Adding...", Toast.LENGTH_SHORT).show();
            addQuest();
            questName.getText().clear();
            traderName.getText().clear();
            lvl.getText().clear();
        }  else if (v == addObj) {
            String obj = objective.getText().toString();
            objList.add(obj);
            objective.getText().clear();
        } else if (v == addRew) {
            rewList.add(reward.getText().toString());
            reward.getText().clear();
        } else if (v == addItem) {
            itemList.add(item.getText().toString());
            item.getText().clear();
        }
    }

    public void setOnClickListeners() {
        addButton.setOnClickListener(this);
        addObj.setOnClickListener(this);
        addRew.setOnClickListener(this);
        addItem.setOnClickListener(this);
    }

    public void addQuest() {
        String qName = questName.getText().toString();
        String tName = traderName.getText().toString();
        int lvlReq = Integer.parseInt(lvl.getText().toString());
        Quest q = new Quest(qName, tName, lvlReq, objList, rewList, itemList);
        Toast.makeText(this, ""+q.toString(), Toast.LENGTH_SHORT).show();
        quests.child(tName).child(qName).setValue(q);
        clearLists();
    }

    public void makeObjectArrays() {
        objList = new ArrayList<String>();
        rewList = new ArrayList<String>();
        itemList = new ArrayList<String>();
        clearLists();
    }

    public void clearLists() {
        objList.clear();
        rewList.clear();
        itemList.clear();
    }


//    private void test() {
//        list = new ArrayList<>();
//        DatabaseReference trader = quests.child("Prapor");
//        DatabaseReference qName = quests.child("Checking");
//        trader.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot d: dataSnapshot.getChildren()) {
//                    Quest q = d.getValue(Quest.class);
//                    list.add(q);
//                    Toast.makeText(ModifyDB.this, ""+list.get(0).getTrader() + " | " + list.get(0).getQuestName() + " | " + list.get(0).getLvlRequirement(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(ModifyDB.this, ""+list.get(0).getObjectives().get(0), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }
}
