package com.example.port.Controler;

import com.example.port.Controler.repo.UserRepo;
import com.example.port.HelloApplication;
import com.example.port.Model.Session;
import com.example.port.Model.User;

import java.sql.SQLException;
import java.util.Objects;

public class UserControler {
    private final UserRepo userRepo;

    public UserControler() {
        this.userRepo = new UserRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "Capucine33?");
    }


    public Session Login(String Name, String pwd){
        User user = userRepo.FindByName(Name);
        Session sessions=new Session(null,false);
        if (user.getUser_id()!=null){
            if (Objects.equals(user.getPasswords(), pwd)){
                Session session = new Session(user,true);
                System.out.println(session);
                System.out.println("Connection Success");
                return session;
            }
            System.out.println("Wrong Passwords");
            return sessions;
        }
        System.out.println("Wrong Name");
        return sessions;
    }

    public Session Register(User user) throws SQLException {
        userRepo.create(user);
        Session session = new Session(user,true);
        return session;
    }



}