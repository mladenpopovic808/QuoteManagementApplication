package http.mainServer;

import http.HttpMethod;
import http.Quote;
import http.Request;
import http.response.Response;
import handlerAndController.RouteHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThreadMain implements Runnable{

    private final Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private int contentLength=0;


    public ServerThreadMain(Socket socket) {
        this.client = socket;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String requestLine=in.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(requestLine); //GET /quote HTTP/1.1
            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");


            do {
                System.out.println(requestLine);
                if(requestLine.contains("Content-Length")) { //Content-Lenght: 20
                    contentLength = Integer.parseInt(requestLine.split(":")[1].trim());
                }
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            if (method.equals(HttpMethod.POST.toString())) {

                char[] buffer = new char[contentLength];
                in.read(buffer); //napuni ostatak teksta u buffer


                String body = new String(buffer);//sve sto je u bufferu pretvori u string
                //System.out.println("Ispisujem body: " +body);

                String [] split1 = body.trim().split("&"); //author=Mladen&quote=citat
                String [] split2 = split1[0].split("=");
                String [] split3 = split1[1].split("=");

                split2[1] = split2[1].replaceAll("\\+", " ");
                split3[1] = split3[1].replaceAll("\\+", " ");//Ovo+je+neki+citat
                ServerMain.quotes.add(new Quote(split2[1],split3[1]));
            }

            Request request = new Request(HttpMethod.valueOf(method), path);
            RouteHandler routeHandler = new RouteHandler();
            Response response = routeHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





















