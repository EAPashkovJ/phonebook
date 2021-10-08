package reportWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Reporter extends Thread {

    @Override
    public void run() {
        File file = new File(".\\out");
        ArrayList<String> list = new ArrayList<>();
        var fileScanner = new FileScanner();
        list = fileScanner.getFile(file, list);
        var writer = new FileWriter();
        try {
            writer.write(list);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
