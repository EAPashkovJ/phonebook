import java.io.IOException;

// http://test.com/path/to/resource
// http://test.com/application/save
public class Application {
    public static void main(String[] args) throws IOException {
        var router = new Router(args);
        router.dispatch();
    }
}
