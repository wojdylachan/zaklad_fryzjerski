public class Klient extends Person {
    String telefon;

    public Klient(String imie, String nazwisko, int wiek, String telefon) {
        super(imie, nazwisko, wiek);
        this.telefon = telefon;
    }

    public void showInfo(){
        System.out.println("Imie: " + imie + ", Nazwisko: " + nazwisko + ", Wiek: " + wiek + ", Telefon: " + telefon);
    }
}
