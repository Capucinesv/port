package com.example.port.Model;

import java.time.LocalDate;

public class User {

    private Long user_id;

    private int age;

    private LocalDate Date_crea;

    private String lastname;

    private String names;

    private String passwords;


    public User(Long user_id, int age, LocalDate date_crea, String lastname, String names, String passwords) {
        this.user_id = user_id;
        this.age = age;
        Date_crea = date_crea;
        this.lastname = lastname;
        this.names = names;
        this.passwords = passwords;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDate_crea() {
        return Date_crea;
    }

    public void setDate_crea(LocalDate date_crea) {
        Date_crea = date_crea;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", age=" + age +
                ", Date_crea=" + Date_crea +
                ", lastname='" + lastname + '\'' +
                ", names='" + names + '\'' +
                ", passwords='" + passwords + '\'' +
                '}';
    }
}