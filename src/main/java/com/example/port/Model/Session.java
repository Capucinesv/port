package com.example.port.Model;

public class Session {
    private User user;
    private Boolean state;

    public Session(User user, Boolean state) {
        this.user = user;
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Session{" +
                "user=" + user +
                ", state=" + state +
                '}';
    }
}