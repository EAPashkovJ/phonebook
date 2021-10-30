package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println("Handling: " + t.getRequestURI());

        var file = new File("./public/index.html");
        var response = Files.readAllBytes(Paths.get(file.getPath()));
        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }
}
