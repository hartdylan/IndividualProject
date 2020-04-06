package edu.augustana.individualproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
 * This class is used to handle the activity for finding hideout upgrades in Escape from Tarkov
 * by using a spinner that has every possible choice and passes data to the hideout upgrader viewer
 * activity.
 */

public class HideoutUpgradeFinder extends AppCompatActivity implements View.OnClickListener {

    // FIELD
    Button submitButton;
    Spinner hideoutSpinner;
    Adapter hideoutAd;
    ArrayList<HideoutUpgrade> upgrades;
    ArrayList<String> upgradeNames;
    String currentHideoutUpgradeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hideout_upgrade_finder);
        getSupportActionBar().setTitle("Hideout - Upgrade finder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        upgrades = new ArrayList<>();
        upgradeNames = new ArrayList<>();
        getHideoutUpgrades();
    }

    /**
     * On click to handle button pressing in this activity.
     * @param v - Parameter to handle the XML objects within this XML layout.
     */
    @Override
    public void onClick(View v) {
        if(v == submitButton) {
            Intent upgradeViewer = new Intent(this, HideoutUpgradeViewer.class);
            upgradeViewer.putExtra("upgrade", currentHideoutUpgradeName);
            startActivity(upgradeViewer);
        }
    }

    /**
     * This method is used to retain the selection by the user in the spinner before they click submit
     * to view the upgrade.
     */
    public void setupHideoutSpinner() {
        hideoutSpinner = findViewById(R.id.hideoutSpinner);
        hideoutAd = new ArrayAdapter<>(getApplicationContext(), R.layout.traderlayout, upgradeNames);
        hideoutSpinner.setAdapter((SpinnerAdapter) hideoutAd);
        hideoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentHideoutUpgradeName = upgradeNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * Gather data from the database in order to populate the spinner with the possible options for hideout
     * upgrades that the user can select.
     */
    public void getHideoutUpgrades() {
        DatabaseReference hideoutRef = FirebaseDatabase.getInstance().getReference("hideout_upgrades");
        hideoutRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    HideoutUpgrade upgrade = snapshot.getValue(HideoutUpgrade.class);
                    upgrades.add(upgrade);
                }
                for(HideoutUpgrade upgrade: upgrades) {
                    upgradeNames.add(upgrade.getHideoutUpgradeName());
                }
                setupHideoutSpinner();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
