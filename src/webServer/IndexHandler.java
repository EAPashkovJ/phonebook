package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println("Handling: " + t.getRequestURI());
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
