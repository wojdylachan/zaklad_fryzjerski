package salon.model;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public Person(String fName, String lName, int age) {
        this.firstName = fName;
        this.lastName = lName;
        this.age = age;
    }
}
