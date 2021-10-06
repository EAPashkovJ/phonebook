package marshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Marshaller {
    void setStream(FileOutputStream fin);

    void process(Object entity) throws IOException;

    void appendProcess(Object entity) throws IOException;
}
