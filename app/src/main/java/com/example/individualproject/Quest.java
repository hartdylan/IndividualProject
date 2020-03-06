package com.example.individualproject;

import java.util.ArrayList;

public class Quest {

    private String questName;
    private String trader;
    private int lvlRequirement;
    private ArrayList<String> objectives;
    private ArrayList<String> rewards;
    private ArrayList<String> questItems;



    public Quest() {

    }

    public Quest(String questName, String trader, int lvlRequirement, ArrayList<String> objectives, ArrayList<String> rewards, ArrayList<String> questItems) {
        this.questName = questName;
        this.trader = trader;
        this.lvlRequirement = lvlRequirement;
        this.objectives = objectives;
        this.rewards = rewards;
        this.questItems = questItems;
    }

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

    public String toString() {
        return questName + " | " + trader + " | " + lvlRequirement;
    }
}
