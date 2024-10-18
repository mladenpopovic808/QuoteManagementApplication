package handlerAndController;

import http.HttpMethod;
import http.Request;
import http.response.Response;

public class RouteHandler {
    public Response handle(Request request) throws Exception {

        //main rute
        if (request.getPath().equals("/quote") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new RouteControllerMain(request)).doGet();

        } else if (request.getPath().equals("/save-quote") && request.getHttpMethod().equals(HttpMethod.POST)) {
            return (new RouteControllerMain(request)).doPost();

        //quote of the day rute
        }else if (request.getPath().equals("/qod") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new RouteControllerQOD(request)).doGet();
        }
        System.out.println(request.getPath());
        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
