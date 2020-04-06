package edu.augustana.individualproject;

import java.util.ArrayList;

/**
 * This class is used to handle a custom object type called HideoutUpgrade
 */

public class HideoutUpgrade {


    // FIELDS
    String hideoutUpgradeName;
    ArrayList<String> items;
    ArrayList<String> requirements;

    /**
     * This is the no-arg constructor needed for using an ArrayList adapter and for reading
     * data in JSON form from the Firebase Real-time Database.
     */
    public HideoutUpgrade() {

    }

    /**
     * This is the constructor that the programmer might use when implementing new hideout upgrades.
     * @param hideoutUpgradeName - String - Name of hideout upgrade
     * @param items - ArrayList<String> - An ArrayList of type String of items required to perform the upgrade.
     * @param requirements - ArrayList<String> - An ArrayList of type String of the requirements needed in order
     *                     to perform the upgrade.
     */
    public HideoutUpgrade(String hideoutUpgradeName, ArrayList<String> items, ArrayList<String> requirements) {
        this.hideoutUpgradeName = hideoutUpgradeName;
        this.items = items;
        this.requirements = requirements;
    }

    // GETTERS
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
