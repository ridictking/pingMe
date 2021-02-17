package ChatClient;

import ChatServer.ChatEnvironment;

import java.io.PrintWriter;

public interface MessageObserver {
   void messageUpdate(PrintWriter writer);
   void setMessage(ChatEnvironment env);
}
