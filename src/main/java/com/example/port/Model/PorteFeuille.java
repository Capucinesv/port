package com.example.port.Model;


import java.util.HashMap;
import java.util.Map;

public class PorteFeuille {

    private Long portefeuille_id;

    private int argent;

    private long user_id;


    private Map<String, Double> actionsMap;

    public PorteFeuille(Long portefeuille_id, int argent,  long user_id) {
        this.portefeuille_id = portefeuille_id;
        this.argent = argent;
        this.user_id = user_id;
        actionsMap = new HashMap<>();
    }

    public boolean buyOrSellAction(String actionName, double actionVal, double actionAmount){
        if(actionAmount == 0.0) return false;
        if(actionAmount < 0.0){
            if(!this.actionsMap.containsKey(actionName)) return false;
            // not having the action
            if((this.actionsMap.get(actionName) + actionAmount) < 0.0) return false;
            // not having enough actions
            if((this.actionsMap.get(actionName) + actionAmount) == 0.0) {
                this.actionsMap.remove(actionName);
                // all sold
            } else {
                this.actionsMap.put(actionName, this.actionsMap.get(actionName) + actionAmount);
            }
        } else {
            if((this.argent - actionVal * actionAmount) < 0.0) return false;
            // not enough money
            double amount = 0.0; // preset owned amount as 0
            if(this.actionsMap.containsKey(actionName)) amount = this.actionsMap.get(actionName);
            // update real amount owned
            this.actionsMap.put(actionName, amount + actionAmount);
            // update amount to map
        }
        this.argent -= (int) (actionVal * actionAmount); // apply sell/buy
        return true;
    }

    public Map<String, Double> getActionsMap(){
        return this.actionsMap;
    }
    public Long getPortefeuille_id() {
        return portefeuille_id;
    }

    public void setPortefeuille_id(Long portefeuille_id) {
        this.portefeuille_id = portefeuille_id;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }




    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "PorteFeuille{" +
                "portefeuille_id=" + portefeuille_id +
                ", argent=" + argent +
                ", user_id=" + user_id +
                '}';
    }
}