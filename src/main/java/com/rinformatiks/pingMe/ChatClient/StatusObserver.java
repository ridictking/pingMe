package com.rinformatiks.pingMe.ChatClient;

import java.io.PrintWriter;

public interface StatusObserver {
    void statusUpdate(PrintWriter writer, String name);
}
