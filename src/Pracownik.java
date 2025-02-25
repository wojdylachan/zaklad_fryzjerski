import java.util.List;
public class Pracownik extends Person {
    String telefon;
    List<TypUslugi> typUslugi;

    public Pracownik(String imie, String nazwisko, int wiek, String telefon, List<TypUslugi> typUslugi) {
        super(imie, nazwisko, wiek);
        this.telefon = telefon;
        this.typUslugi = typUslugi;
    }
}
