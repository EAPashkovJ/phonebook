package marshaller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected AppendObjectOutputStream() throws IOException, SecurityException {
    }
    @Override
    protected void writeStreamHeader() throws IOException
    {
// do not write a header, but reset:
// this line added after another question
// showed a problem with the original
        reset();
    }
}
