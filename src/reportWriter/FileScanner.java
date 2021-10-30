package reportWriter;

import java.io.File;
import java.util.ArrayList;
public class FileScanner {


    public ArrayList<String> getFile(File file, ArrayList<String> fileList) {
        var files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                this.getFile(f, fileList);
            } else if (f.isFile()) {//
                fileList.add(f.getPath());
            }
        }
        return fileList;
    }
}
