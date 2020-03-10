package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HideoutActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("hideout_upgrades");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hideout);
        getSupportActionBar().setTitle("Hideout upgrade finder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> items = new ArrayList<>();
        items.add("10x CPU fans");
        items.add("5x power supply units");
        items.add("5x power cords");
        items.add("1x electric drill");

        ArrayList<String> requirements = new ArrayList<>();
        requirements.add("Level 2 Intelligence Center");

        HideoutUpgrade bitcoinFarm = new HideoutUpgrade("Bitcoin Farm - Level 1", items, requirements);

        ref.child(bitcoinFarm.getHideoutUpgradeName()).setValue(bitcoinFarm);
    }
}
