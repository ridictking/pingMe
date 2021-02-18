package com.rinformatiks.pingMe.ChatClient;

import com.rinformatiks.pingMe.ChatServer.ChatEnvironment;

import java.io.PrintWriter;

public interface MessageObserver {
   void messageUpdate(PrintWriter writer);
   void setMessage(ChatEnvironment env);
}
