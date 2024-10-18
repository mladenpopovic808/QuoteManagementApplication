package handlerAndController;

import com.google.gson.Gson;
import http.Quote;
import http.mainServer.ServerMain;
import http.response.ResponseHTML;
import http.response.ResponseRedirect;
import http.response.Response;


import java.io.*;
import java.net.Socket;

public class RouteControllerMain extends Controller {


    PrintWriter out = null;
    BufferedReader in = null;
    String listItems = "";
    Quote quote = null;


    public RouteControllerMain(http.Request request) {
        super(request);
    }

    @Override
    public Response doGet() {


            //getujemo quote of the day

        try {
            Socket socket = new Socket("localhost", 8200); //pomocni server
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET /qod HTTP/1.1\r\n"); //Saljemo request za quoteOfTheDay

            String requestLine = in.readLine();
            int contentLength = 0;
            do {
                if(requestLine.contains("Content-Length")) {
                    contentLength = Integer.parseInt(requestLine.split(":")[1].trim());
                }
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            char[] buffer = new char[contentLength];//qod
            in.read(buffer);


            String quoteResponse = new String(buffer);
            Gson gson = new Gson();
            //System.out.println("ispusujem ti quote response"+quoteResponse);
            quote = gson.fromJson(quoteResponse, Quote.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //upisani qoute-ovi
        for (Quote q: ServerMain.quotes) {
            listItems = listItems + "<li style= border: 1px solid black>"+ q.getAuthor() + ": " + q.getQuote() + "</li>";
        }

        String htmlBody = "" +
                "<form method=\"POST\" action=\"/save-quote\">" +
                "<label>Author: </label>"  +"<input name=\"author\" type=\"text\"><br><br>" +
                "<label>Quote: </label>"  + "<input name=\"quote\" type=\"text\"><br><br>" +
                "<button>Save Quote</button>" +
                "</form>" +
                "<h1> Quote of the day: </h1>" +
                "<p>" +quote.getAuthor()+" : "+quote.getQuote()+ "<p>" +
                "<h3> Other quotes in list : </h3>" +
                "<ul style= border: 1px solid black>" + listItems + "</ul>";



        String content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";
        return new ResponseHTML(content);
    }

    @Override
    public Response doPost() {

        return new ResponseRedirect("/quote");
    }
}
