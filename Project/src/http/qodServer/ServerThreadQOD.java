package http.qodServer;

import http.HttpMethod;
import http.Request;
import http.response.Response;
import handlerAndController.RouteHandler;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThreadQOD implements Runnable {


    private final Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThreadQOD(Socket socket) {
        this.client = socket;

        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

        try {

            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));


            Request request = new Request(HttpMethod.valueOf(method), path);

            RouteHandler routeHandler = new RouteHandler();
            Response response = routeHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();
        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}



























