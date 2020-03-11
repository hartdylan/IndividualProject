package edu.augustana.individualproject;

import java.util.ArrayList;

public class HideoutUpgrade {

    String hideoutUpgradeName;
    ArrayList<String> items;
    ArrayList<String> requirements;

    public HideoutUpgrade(String hideoutUpgradeName, ArrayList<String> items, ArrayList<String> requirements) {
        this.hideoutUpgradeName = hideoutUpgradeName;
        this.items = items;
        this.requirements = requirements;
    }

    public String getHideoutUpgradeName() {
        return hideoutUpgradeName;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }
}
