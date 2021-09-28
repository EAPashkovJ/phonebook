
import entity.Person;

import java.util.Random;

public class Singleton {

    static {
        instance = new Singleton();
    }

    private static Singleton instance;

    private String name;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void doSomething() {
        System.out.println("Hello, World!");
        System.out.println(this.hashCode());
    }

    static void changeName(Singleton singleton) {
        singleton.setName(String.valueOf(new Random().nextLong()));
    }

    public static void main(String[] args) {
        Singleton.getInstance().doSomething();
        Singleton.getInstance().doSomething();
        System.out.println(Singleton.getInstance().equals(getInstance()));

        var singleton = Singleton.getInstance();
        System.out.println(singleton.getName());
        changeName(singleton);
        System.out.println(singleton.getName());

        var person = new Person(1);
        System.out.println("person: " + person);
//        person.setPassword(String.valueOf(new Random().nextInt(100)));
        System.out.println("person (modified password): " + person);
//        person.setPasswordViaSetter("password");
        System.out.println("person (modified password): " + person);
    }
}
