package handlerAndController;

import com.google.gson.Gson;
import http.Quote;
import http.Request;
import http.qodServer.ServerQOD;
import http.response.ResponseQOD;
import http.response.Response;



import java.util.Random;

public class RouteControllerQOD extends Controller {

        public RouteControllerQOD(Request request) {
            super(request);
        }

        @Override
        public Response doGet() {
            Random rand=new Random();

            int n=rand.nextInt(ServerQOD.quotesOfTheDay.size());
            Quote quote=ServerQOD.quotesOfTheDay.get(n);
            Gson gson=new Gson();
            String json=gson.toJson(quote);
            //System.out.println("ispisujem ti json kada ga pomocni vraca "+json);
            return new ResponseQOD(json);

        }

        @Override
        public Response doPost() {
            return null;
        }
}
