import java.util.List;

public class ZakladFryzjerski {
    List<Usluga> uslugi;
    List<Rezerwacja> rezerwacje;
    public static void main(String[] args) {
        System.out.println("Zaklad Fryzjerski - Jan Wojdyla");
        while(true)
        System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - quit / 5 - ");
    }
}

// Opis: System umożliwia zarządzanie usugami fryzjerskimi, rezerwacjami, pracownikami oraz historiąwykonanych uslug

// Wymagania funkcjonalne:
// Klienci mogą rezerwowac usugi fryzjerskie online.
// Pracownicy mogą przeglądać kalendarz rezerwacji
// System przechowuje historię usług wykonanych na rzech klientów
// Możliwość generowania raportów o sprzedaży usług
// System umożliwia zarządzanie asortymentem (np. kosmetyki do włosów)

// Przykładowe klasy i enumy
