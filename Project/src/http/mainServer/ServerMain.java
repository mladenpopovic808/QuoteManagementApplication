package http.mainServer;

import http.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerMain {

    public static final CopyOnWriteArrayList<Quote> quotes = new CopyOnWriteArrayList<>();
    public static final int PORT = 8100;


    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (true) {
                Socket socket = ss.accept();
                new Thread(new ServerThreadMain(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
