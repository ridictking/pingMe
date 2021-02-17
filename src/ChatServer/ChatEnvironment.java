package ChatServer;

import ChatClient.MessageObserver;
import ChatClient.User;

import java.io.PrintWriter;

public interface ChatEnvironment {
    void registerUser(MessageObserver user);
    void removeUser(MessageObserver user);
    void notifyUsers(PrintWriter writer);
    Object getUpdate(MessageObserver user);
}
