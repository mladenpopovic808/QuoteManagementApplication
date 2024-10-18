package http.response;

public class ResponseRedirect extends Response{

        private final String redirectLocation;

        public ResponseRedirect(String redirectLocation) {
            this.redirectLocation = redirectLocation;
        }

        @Override
        public String getResponseString() {


            return "HTTP/1.1 301 OK\r\nLocation: "+this.redirectLocation+"\r\n\r\n";

        }
}
