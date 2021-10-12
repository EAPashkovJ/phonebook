
import controller.ApplicationController;
import controller.IController;
import controller.PhonebookController;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;

public class Router {
    private String[] arguments;
    private Connection connection;

    public Router() {
    }

    public Router(String[] arguments) {
        this.arguments = arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public void setDatabaseConnection(Connection connection) {
        this.connection = connection;
    }

    public IController getController() {
        IController controller;
        var action = this.arguments[0];
        if (action.startsWith("application/")) {
            controller = new ApplicationController();
        } else if (action.startsWith("phonebook/")) {
            controller = new PhonebookController();
        } else {
            throw new IllegalArgumentException("Wrong action: " + action);
        }
        controller.setDatabaseConnection(connection);
        return controller;
    }

    public void dispatch() {
        getController().process(Arrays.asList(this.arguments));
    }
}
