package edu.augustana.individualproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HideoutUpgradeViewer extends AppCompatActivity {

    TextView upgradeName;
    ImageView img;
    ListView itemListView, requirementListView;
    ArrayAdapter<String> itemAd, reqAd;
    String upgrade;
    ArrayList<HideoutUpgrade> upgrades;
    int drawableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hideout_upgrade_viewer);
        getSupportActionBar().setTitle("Hideout - Upgrade viewer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        upgrades = new ArrayList<>();
        initializeViewIDs();
        getHideoutUpgradeInfo();
    }

    private void initializeViewIDs() {
        img = findViewById(R.id.upgradeImg);
        upgradeName = findViewById(R.id.upgradeName);
        itemListView = findViewById(R.id.itemListView);
        requirementListView = findViewById(R.id.requirementListView);
    }

    private void getHideoutUpgradeInfo() {
        upgrade = getIntent().getStringExtra("upgrade");
        DatabaseReference upgradeRef = FirebaseDatabase.getInstance().getReference("hideout_upgrades").child(upgrade);
        upgradeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HideoutUpgrade hideoutUpgrade = dataSnapshot.getValue(HideoutUpgrade.class);
                setupHideoutUpgradeInfo(hideoutUpgrade);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupHideoutUpgradeInfo(HideoutUpgrade hideoutUpgrade) {
        Resources r = getResources();
        drawableId = r.getIdentifier(hideoutUpgrade.getHideoutUpgradeName().replaceAll("[^a-zA-Z]", "").toLowerCase(), "drawable", "com.example.individualproject");
        img.setImageResource(drawableId);
        upgradeName.setText("Hideout upgrade: " + hideoutUpgrade.getHideoutUpgradeName());
        itemAd = new ArrayAdapter<String>(HideoutUpgradeViewer.this, R.layout.listlayout, hideoutUpgrade.getItems());
        itemListView.setAdapter(itemAd);
        reqAd = new ArrayAdapter<String>(HideoutUpgradeViewer.this, R.layout.listlayout, hideoutUpgrade.getRequirements());
        requirementListView.setAdapter(reqAd);
    }


}
