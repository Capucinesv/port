package com.example.port.Model;

public class Actions {

    private Long action_id;
    private Long ActionsType;
    private String NomAction;
    private int nbr;
    private int total;
    private Long portefeuille_id;

    public Actions(Long action_id, Long actionsType, String nomAction, int nbr, int total, Long portefeuille_id) {
        this.action_id = action_id;
        ActionsType = actionsType;
        NomAction = nomAction;
        this.nbr = nbr;
        this.total = total;
        this.portefeuille_id = portefeuille_id;
    }


    public Long getAction_id() {
        return action_id;
    }

    public void setAction_id(Long action_id) {
        this.action_id = action_id;
    }

    public Long getActionsType() {
        return ActionsType;
    }

    public void setActionsType(Long actionsType) {
        ActionsType = actionsType;
    }

    public String getNomAction() {
        return NomAction;
    }

    public void setNomAction(String nomAction) {
        NomAction = nomAction;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getPortefeuille_id() {
        return portefeuille_id;
    }

    public void setPortefeuille_id(Long portefeuille_id) {
        this.portefeuille_id = portefeuille_id;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "action_id=" + action_id +
                ", ActionsType=" + ActionsType +
                ", NomAction='" + NomAction + '\'' +
                ", nbr=" + nbr +
                ", total=" + total +
                ", portefeuille_id=" + portefeuille_id +
                '}';
    }
}