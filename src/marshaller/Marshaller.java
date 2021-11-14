package marshaller;

import java.io.FileOutputStream;
import java.io.IOException;

public interface Marshaller {
    void setStream(FileOutputStream fin);

    void process(Object entity) throws IOException;

    void appendProcess(Object entity) throws IOException;
}
