package com.rinformatiks.pingMe.ChatServer;

import com.rinformatiks.pingMe.ChatClient.MessageObserver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PTPChat implements ChatEnvironment {
    private List<MessageObserver> users = new ArrayList<>();
    private String message;

    public void registerUser(MessageObserver user) {
        if(user != null && !users.contains(user)) users.add(user);
    }


    public void removeUser(MessageObserver user) {
        users.remove(user);
    }


    public void notifyUsers(PrintWriter writer) {
        for(MessageObserver o : users){
            o.messageUpdate(writer);
        }
    }
    public Object getUpdate(MessageObserver user) {
        return this.message;
    }

    public void postMessage(String msg,PrintWriter writer){
        this.message = msg;
        notifyUsers(writer);
    }
}
