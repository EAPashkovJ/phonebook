import webServer.SaveHandler;
import webServer.IndexHandler;
import webServer.PhonebookHandler;
import com.sun.net.httpserver.HttpServer;
import webServer.TestBTHundler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebApplication {
    public static void main(String[] args) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/testBT", new TestBTHundler());
            server.createContext("/phonebook", new PhonebookHandler());
            server.createContext("/save" , new SaveHandler());
            server.createContext("/", new IndexHandler());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        server.setExecutor(null); // creates a default executor

        System.out.println("Starting HTTP service on :8000...");
        server.start();
    }
}
