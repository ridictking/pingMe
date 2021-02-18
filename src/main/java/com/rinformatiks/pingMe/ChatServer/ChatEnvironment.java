package com.rinformatiks.pingMe.ChatServer;

import com.rinformatiks.pingMe.ChatClient.MessageObserver;

import java.io.PrintWriter;

public interface ChatEnvironment {
    void registerUser(MessageObserver user);
    void removeUser(MessageObserver user);
    void notifyUsers(PrintWriter writer);
    Object getUpdate(MessageObserver user);
}
