package http.qodServer;

import http.Quote;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerQOD {

        public static final int PORT = 8200;
        public static final ArrayList<Quote> quotesOfTheDay = new ArrayList<>();


        public static void main(String[] args) {
            quotesOfTheDay.add(new Quote("Arthur Shelby", "By order of the Peaky Blinders."));
            quotesOfTheDay.add(new Quote("Alfie Solomons", "I'm a businessman. Bloodier than most, but... respectable."));
            quotesOfTheDay.add(new Quote("Arthur Shelby", "The Shelby family is cursed."));
            quotesOfTheDay.add(new Quote("Tommy Shelby", "I don't pay for suits. My suits are on the house, or the house burns down."));
            quotesOfTheDay.add(new Quote("John Shelby", "I don't want to be a man that walks the streets looking over his shoulder.."));
            quotesOfTheDay.add(new Quote("Ada Shelby", "You can change what you do, but you can't change what you want."));
            quotesOfTheDay.add(new Quote("Thomas Shelby", "Everyone's a whore, Grace. We just sell different parts of ourselves"));
            quotesOfTheDay.add(new Quote("Thomas Shelby", "I'm not a traitor to my class. I am just an extreme example of what a working man can achieve."));
            quotesOfTheDay.add(new Quote("Thomas Shelby", "I'm not a politician, I'm a businessman.."));
            quotesOfTheDay.add(new Quote("Thomas Shelby", "I'm Shelby. I go by one name, like Madonna."));

            try {
                ServerSocket ss = new ServerSocket(PORT);
                while (true) {
                    Socket socket = ss.accept();
                    new Thread(new ServerThreadQOD(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



}
