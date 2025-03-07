import java.util.List;
public class Pracownik extends Person {
    String telefon;
    List<TypUslugi> typUslugi;
    String email;
    String haslo;

    public Pracownik(String imie, String nazwisko, int wiek, String telefon, String email, String haslo, List<TypUslugi> typUslugi) {
        super(imie, nazwisko, wiek);
        this.telefon = telefon;
        this.typUslugi = typUslugi;
        this.haslo = haslo;
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public List<TypUslugi> getTypUslugi() {
        return typUslugi;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getEmail() {
        return email;
    }

    public void showInfo(){
        System.out.println("Imie: " + imie + ", Nazwisko: " + nazwisko + ", Wiek: " + wiek + ", Telefon: +" + telefon.substring(0, 2) + " " + telefon.substring(2, 5) + " " + telefon.substring(5, 8) + " " + telefon.substring(8) + ", Email: " + email);
    }
}
