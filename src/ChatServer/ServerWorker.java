package ChatServer;

import ChatClient.User;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;

public class ServerWorker  extends Thread{

    private final Socket connection;
    private  String name;
    private Server server;
    public PrintWriter writer;

    public ServerWorker(Socket connection, Server server) {
        this.connection = connection;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            handleConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnection() throws IOException {
        InputStream inputStream = connection.getInputStream();
        OutputStream outputStream = connection.getOutputStream();

        writer = new PrintWriter(outputStream,true);
        writer.println("Hello Client");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line ;
        while ((line= reader.readLine()) != null){
            if (StringUtils.split(line)[0].equalsIgnoreCase("login")) {
                boolean login = handleLogin(line, writer);
                if(login){
                    while((line= reader.readLine()) != null){
                        if(line.equalsIgnoreCase("logout")){
                            handleLogout(line,writer);
                            break;
                        }else{
                            handleChat(line,writer);
                        }
                    }
                }
                else writer.println("Unknown User");
            }else if(line.equalsIgnoreCase("quit")){
                writer.println("Thanks for using pingME");
                break;
            }else{
                String s = "Unknown command " + line;
                writer.println(s);
            }
        }
        System.out.println(connection + " Disconnected from server");
        connection.close();
    }

    private void handleChat(String line, PrintWriter writer) {
        for(ServerWorker s : server.workers){
            if (this == s)s.writer.println( "You : " + line);
            else s.writer.println( name + ": " + line);
        }
    } private void handleLogout(String line, PrintWriter writer) {
        for(ServerWorker s : server.workers){
            if (this == s)s.writer.println( "You logged out");
            else s.writer.println( name + " logged out" );
        }
        this.server.workers.remove(this);
    }

    private boolean handleLogin(String line, PrintWriter writer){
        this.server.workers.add(this);
        String[] tokens = StringUtils.split(line);
        String token = tokens[0];
        User user = new User(tokens[1], tokens[2]);
        if (tokens.length == 3) {
            name = tokens[1];
            String password = tokens[2];
            if((name.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
                ||(name.equalsIgnoreCase("user") && password.equalsIgnoreCase("user"))
                ||(name.equalsIgnoreCase("ade") && password.equalsIgnoreCase("ade"))){

                for(ServerWorker s : server.workers){
                    if(s == this) s.writer.println("You've logged in successfully");
                    else s.writer.println("User "+ name + " logged in successfully");
                }
                System.out.println("User "+ name + " logged in successfully");
                return true;
            }else{
                writer.println("Invalid username or password");
            }
        }
        return false;
    }
}
