public class Person {
    protected String fullName;
    protected int registrationNumber;
    protected String email;
    //Constructor
    public Person(String fn, int rn, String email) {
        this.fullName = fn;
        this.registrationNumber = rn;
        this.email = email;
    }
    //Getter
    public String getFullName() {
        return fullName;
    }

    public int registrationNumber() {
        return registrationNumber;
    }

    public String getEmail() {
        return email;
    }
    protected Person() {
    }
}

