package reportWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportWriter {

    public void write(List<String> list) throws IOException {
        java.io.FileWriter writer = new java.io.FileWriter("./report.txt");
        writer.write("This is a files usage report\n");
        list.stream().forEach(text -> {
            try {
                writer.write(text);
                writer.write("\n");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        writer.flush();
        writer.close();

    }
}
