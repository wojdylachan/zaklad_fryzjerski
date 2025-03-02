public class Usluga {
    String nazwa;
    double cena;
    int czasTrwania;
    TypUslugi typUslugi;
    String opis;

    public Usluga(String nazwa, double cena, int czasTrwania, TypUslugi typUslugi, String opis) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.czasTrwania = czasTrwania;
        this.typUslugi = typUslugi;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public TypUslugi getTypUslugi() {
        return typUslugi;
    }

    public String getOpis() {
        return opis;
    }

    public void showInfo() {
        System.out.println(nazwa + " | " + (int) cena + " zl | " + (int) czasTrwania + " min | " + opis);
    }
    public void showInfo2() {
        System.out.println(nazwa + " | " + (int) cena + " zl | " + (int) czasTrwania + " min");
    }
}
