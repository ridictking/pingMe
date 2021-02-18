package com.rinformatiks.pingMe.ChatClient;

import com.rinformatiks.pingMe.ChatServer.ChatEnvironment;

import java.io.PrintWriter;

public class User implements StatusObserver,MessageObserver {
    private String name;
    private String password;
    private String status;
    private String message;
    private ChatEnvironment chatEnvironment;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void statusUpdate(PrintWriter writer, String name) {
        writer.println(name + " Logged in successfully");
    }

    @Override
    public void messageUpdate(PrintWriter writer) {
        String msg = (String)chatEnvironment.getUpdate(this);
        writer.println(msg + " Logged in");
    }

    @Override
    public void setMessage(ChatEnvironment env) {
        this.chatEnvironment = env;
    }
}
