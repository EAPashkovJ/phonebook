package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhonebookFileStorage extends FileStorage {
    private String filePath;

    public PhonebookFileStorage(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }


    @Override
    public List<Person> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Person> list = null;
        try {
            list = mapper.readValue(new File(filePath), new TypeReference<ArrayList<Person>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(Object entity) {
        Person person = (Person) entity;
        File file = new File(filePath);
        var list = new ArrayList<Person>();
        if (file.exists()) {
            list = (ArrayList<Person>) this.findAll();
        }
        list.add(person);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
