import reportWriter.Reporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// http://test.com/path/to/resource
// http://test.com/application/save
public class Application {
    private static Connection getConnection() {
        try {
            // loading properties
            var properties = new Properties();
            try (var in = Files.newInputStream(Paths.get("database.properties"))) {
                properties.load(in);
            }

            Class.forName("com.mysql.cj.jdbc.Driver"); // loading MySQL driver for java.sql

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));

        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        var router = new Router(args);
        router.setDatabaseConnection(getConnection());
        router.dispatch();

        var reporter = new Reporter();
        reporter.start();

    }
}
