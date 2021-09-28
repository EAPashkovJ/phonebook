package storage;



import marshaller.Marshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class FileStorage<E> implements Storage<E> {
    private String filePath;
    private Marshaller marshaller;
    private Class<E> entityClass;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Class<E> getEntityClass() {
        return this.entityClass;
    }

    @Override
    public void setEntityClass(Class<E> clazz) {
        this.entityClass = clazz;
    }

    @Override
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public void save(Object person) {
        File file = new File(this.filePath);
        try  {


            if(file.exists()){
                var fin = new FileOutputStream(file, true);
                this.marshaller.setStream(fin);
                this.marshaller.appendProcess(person);
            }else {
                var fin = new FileOutputStream(file);
                this.marshaller.setStream(fin);
                this.marshaller.process(person);

            }

//            fin.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
