public class Klient extends Person {
    String telefon;

    public Klient(String imie, String nazwisko, int wiek, String telefon) {
        super(imie, nazwisko, wiek);
        this.telefon = telefon;
    }

    public void showInfo(){
        System.out.println("Imie: " + imie + ", Nazwisko: " + nazwisko + ", Wiek: " + wiek + ", Telefon: +" + telefon.substring(0, 2) + " " + telefon.substring(2, 5) + " " + telefon.substring(5, 8) + " " + telefon.substring(8));
    }
}
