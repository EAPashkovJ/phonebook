package controller;




import dao.PhonebookDAO;
import entity.Person;
import mapper.PersonMapper;
import marshaller.PersonMarshaller;
import storage.FileStorage;

import java.util.Collections;
import java.util.List;

public class PhonebookController implements IController {
    @Override
    public void process(List<String> arguments) {
        var storage = new FileStorage<Person>("./phonebook.txt");
        storage.setMarshaller(new PersonMarshaller());
        storage.setEntityClass(Person.class);

        var dao = new PhonebookDAO(Collections.singletonList(storage));
        switch (arguments.get(0).replace("phonebook/", "")) {
            case "getIndex" -> {
                var currentTime = System.currentTimeMillis();
                System.out.println(dao.findByIndex(Integer.parseInt(arguments.get(1))));
                System.out.println(System.currentTimeMillis() - currentTime);

            }
            case "index" -> {
                dao.saveIndex();
            }
            case "generate" -> {
                var mapper = new PersonMapper();
                for (int i = 0; i < 150; i++) {
                    dao.save(mapper.toEntity2(arguments));
                }
            }
            case "save" -> {
                var mapper = new PersonMapper();
                dao.save(mapper.toEntity(arguments));
            }
            case "call" -> {
                var currentTime = System.currentTimeMillis();
                var person = dao.findByLastname(arguments.get(1));
                System.out.println(person);
                System.out.println(System.currentTimeMillis() - currentTime);
                //person.getPhoneNumber();
            }
            case "delete" -> {
                dao.delete(Integer.parseInt(arguments.get(1)));
            }
            case "find" -> {
                var person = dao.find(Integer.parseInt(arguments.get(1)));
                if (person != null) {
                    System.out.println(person);
                } else {
                    System.err.println("Person not found");
                }
            }
            case "list" -> {
                List<Person> list = dao.findAll();
                for (Person person : list) {
                    System.out.println(person);
                }

            }
            case "find_by_phone" -> {
                var personList = dao.findBy(person -> person.getPhoneNumber().startsWith(arguments.get(1)));
                personList.forEach(System.out::println);
            }
        }
    }
}
