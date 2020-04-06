package edu.augustana.individualproject;

import java.util.ArrayList;

/**
 * This class is used to handle the custom object type of Quest
 */

public class Quest {

    // FIELDS
    private String questName;
    private String trader;
    private int lvlRequirement;
    private ArrayList<String> objectives;
    private ArrayList<String> rewards;
    private ArrayList<String> questItems;


    /**
     * This is the no-arg constructor needed for using an ArrayList adapter and for reading
     * data in JSON form from the Firebase Real-time Database.
     */
    public Quest() {

    }

    /**
     * This constructor is used to handle
     * @param questName - String - Name of the quest
     * @param trader - String - Name of the trader
     * @param lvlRequirement - int - Level required for quest unlock
     * @param objectives - ArrayList<String> - String ArrayList of the objectives for the quest
     * @param rewards - ArrayList<String> - String ArrayList of the rewards obtained for completing the quest
     * @param questItems - ArrayList<String> - String ArrayList of the quest items required for the quest
     */
    public Quest(String questName, String trader, int lvlRequirement, ArrayList<String> objectives, ArrayList<String> rewards, ArrayList<String> questItems) {
        this.questName = questName;
        this.trader = trader;
        this.lvlRequirement = lvlRequirement;
        this.objectives = objectives;
        this.rewards = rewards;
        this.questItems = questItems;
    }


    // GETTERS
    public String getQuestName() {
        return questName;
    }

    public String getTrader() {
        return trader;
    }

    public int getLvlRequirement() {
        return lvlRequirement;
    }

    public ArrayList<String> getObjectives() {
        return objectives;
    }

    public ArrayList<String> getRewards() {
        return rewards;
    }

    public ArrayList<String> getQuestItems() {
        return questItems;
    }
}
