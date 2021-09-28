package storage;



import marshaller.Marshaller;

import java.util.List;

public class DatabaseStorage<E> extends BaseStorage implements Storage<E> {
    @Override
    public Class getEntityClass() {
        return null;
    }

    @Override
    public void setEntityClass(Class<E> clazz) {

    }

    @Override
    public void setMarshaller(Marshaller marshaller) {

    }

    @Override
    public String getFilePath() {
        return null;
    }

    @Override
    public void save(Object save) {

    }

    @Override
    public List<E> findAll() {
        return Storage.super.findAll();
    }
}
