package marshaller;


import entity.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PersonMarshaller implements Marshaller {
    private FileOutputStream fout;

    @Override
    public void appendProcess(Object entity) throws IOException {
        var person = (Person) entity;
        var ois = new AppendObjectOutputStream(fout);
        ois.writeObject(person);
        ois.flush();

    }

    @Override
    public void setStream(FileOutputStream fin) {
        this.fout = fin;
    }

    @Override
    public void process(Object entity) throws IOException {
        var person = (Person) entity;
        var ois = new ObjectOutputStream(fout);
        ois.writeObject(person);
        ois.flush();
//        ois.close();




//        fin.write(person.getId().toString().getBytes(StandardCharsets.UTF_8));
//        fin.write("/".getBytes(StandardCharsets.UTF_8));
//        fin.write(person.getFirstname().getBytes(StandardCharsets.UTF_8));
//        fin.write("/".getBytes(StandardCharsets.UTF_8));
//        fin.write(person.getLastname().getBytes(StandardCharsets.UTF_8));
//        fin.write("/".getBytes(StandardCharsets.UTF_8));
//        fin.write(person.getAge().toString().getBytes(StandardCharsets.UTF_8));
//        fin.write("/".getBytes(StandardCharsets.UTF_8));
//        fin.write(person.getPhoneNumber().getBytes(StandardCharsets.UTF_8));
//        fin.write("/".getBytes(StandardCharsets.UTF_8));
//        fin.write(person.getAddress().getBytes(StandardCharsets.UTF_8));
//        fin.write("\n".getBytes(StandardCharsets.UTF_8));
    }

}
