package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.PhonebookDAO;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        var properties = new Properties();
        var dao = new PhonebookDAO(getConnection(properties));
        System.out.println("Handling: " + t.getRequestURI());

        var file = new File("./public/index.html");
//        var response = Files.readAllBytes(Paths.get(file.getPath()));
        String stringResponse = Files.readString(Paths.get(file.getPath()));
        var personList = dao.findAll();
        var builder = new StringBuilder();
        builder.append("<table class = \"person\">");
        builder.append("<tr>");
        builder.append("<td>Name</td>");
        builder.append("<td>Lastname</td>");
        builder.append("<td>Age</td>");
        builder.append("<td>Phone number</td>");
        builder.append("<td>Address</td>");
        builder.append("</tr>");
        personList.forEach(x -> {
            builder.append("<tr>");
            builder.append("<td>" + x.getFirstname() + "</td>");
            builder.append("<td>" + x.getLastname() + "</td>");
            builder.append("<td>" + x.getAge() + "</td>");
            builder.append("<td>" + x.getPhoneNumber() + "</td>");
            builder.append("<td>" + x.getAddress() + "</td>");
            builder.append("</tr>");

        });
        builder.append("</table>");
        var response = stringResponse.replace("{{content}}", builder.toString()).getBytes(StandardCharsets.UTF_8);


        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();


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