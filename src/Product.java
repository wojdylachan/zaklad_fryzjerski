import java.util.ArrayList;
import java.util.List;

class Product {
    String nazwa;
    double cena;
    int ilosc;
    String kategoria; // Szampon, Odżywka, Stylizacja

    public Product(String nazwa, double cena, int ilosc, String kategoria) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
        this.kategoria = kategoria;
    }

    public void showInfo() {
        System.out.println(nazwa + " | " + kategoria + " | " + cena + " PLN | Ilość: " + ilosc);
    }
}

