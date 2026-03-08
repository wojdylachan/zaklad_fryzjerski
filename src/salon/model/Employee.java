package salon.model;

import salon.enums.ServiceType;

import java.util.List;
public class Employee extends Person {
    private String telephone;
    private List<ServiceType> serviceType;
    private String email;
    private String password;
    private static int counter2;
    private final int employeeID;

    public Employee(String name, String surname, int age, String telephone, String email, String password, List<ServiceType> serviceType) {
        super(name, surname, age);
        this.telephone = telephone;
        this.serviceType = serviceType;
        this.password = password;
        this.email = email;
        this.employeeID = ++counter2;
    }

    public List<ServiceType> getServiceType() {
        return serviceType;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void showInfo(){
        System.out.println("Pracownik: " + this.getFirstName() + " " + this.getLastName() + " | Specjalizacje: "  + serviceType);
    }
    public void showInfoFull(){
        System.out.println("ID " + employeeID + " | Pracownik: " + this.getFirstName() + " " + this.getLastName() + " | Specjalizacje: " + serviceType + " | Wiek: " + this.getAge() + " | Telefon: +" + telephone.substring(0, 2) + " " + telephone.substring(2, 5) + " " + telephone.substring(5, 8) + " " + telephone.substring(8) + " | Email: " + email);
    }
}
