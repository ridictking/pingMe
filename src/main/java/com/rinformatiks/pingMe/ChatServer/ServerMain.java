package com.rinformatiks.pingMe.ChatServer;

public class ServerMain {
    public static void main(String[] args) {
        int serverPort = 8818;
        Server server = new Server(serverPort);
        server.start();
    }
}
