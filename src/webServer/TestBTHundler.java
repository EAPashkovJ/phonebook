package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.ApplicationDAO;
import entity.Application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestBTHundler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var file = new File("./public/testBT.html");

        var response = Files.readString(Paths.get(file.getPath()));
        var builder = new StringBuilder();
        Properties properties = new Properties();
        var dao = new ApplicationDAO(getConnection(properties));

        var method = exchange.getRequestMethod();
        if (method.equals("POST")) {
            var inputStream = exchange.getRequestBody();
            int c = 0;
            var body = new StringBuilder();
            while ((c = inputStream.read()) != -1) {
                body.append((char) c);
            }
            var args = Arrays.stream(body.toString().split("&")).collect(Collectors.toList());
            var fields = new HashMap<String, String>();
            args.forEach(arg -> fields.put(arg.split("=")[0], arg.split("=")[1]));
            var testBTHundler = new Application();
            testBTHundler.setFirstname(fields.get("firstName"));
            testBTHundler.setLastname(fields.get("lastName"));
            testBTHundler.setAddress(fields.get("address"));
            testBTHundler.setAge(Integer.parseInt(fields.get("age")));
            testBTHundler.setStatus(Integer.parseInt(fields.get("status")));
            dao.save(testBTHundler);
        }


        var list = dao.findAll();
        list.forEach(x -> {
            builder.append("<tr>");
            builder.append("<th scope=\"row\">").append(x.getId()).append("</th>");
            builder.append("<td>").append(x.getFirstname()).append("</td>");
            builder.append("<td>").append(x.getLastname()).append("</td>");
            builder.append("<td>").append(x.getAge()).append("</td>");
            builder.append("<td>").append(x.getAddress()).append("</td>");
            builder.append("<td>").append(x.getStatus()).append("</td>");
            builder.append("</tr>");
        });
        response = response.replace("{{content}}", builder.toString());
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
    }

    private Connection getConnection(Properties properties) {
        Connection connection = null;
        try (var in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);

            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));


        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();

        }
        return connection;
    }
}
