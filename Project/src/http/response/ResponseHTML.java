package http.response;

public class ResponseHTML extends Response{

        private final String html;

        public ResponseHTML(String html) {
            this.html = html;
        }


        @Override
        public String getResponseString() {
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
            response += html;
            return response;
        }

}
