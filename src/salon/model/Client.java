package salon.model;

public class Client extends Person {
    private String telephone;
    private static int counter;
    private final int clientID;

    public Client(String name, String surname, int age, String telephone) {
        super(name,surname, age);
        this.telephone = telephone;
        this.clientID = ++counter;
    }
    public void showInfo(){
        System.out.println("ID: " + clientID + " | Klient: " + this.getFullName() +  " | Wiek: " + age + " | Telefon: +" + telephone.substring(0, 2) + " " + telephone.substring(2, 5) + " " + telephone.substring(5, 8) + " " + telephone.substring(8));
    }
    public String showName() {
        return (firstName + " " + lastName);
    }
}
