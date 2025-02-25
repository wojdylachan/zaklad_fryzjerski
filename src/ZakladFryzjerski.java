import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ZakladFryzjerski {
    List<Usluga> uslugi;
    List<Rezerwacja> rezerwacje;
    static ArrayList<Klient> klienci = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Zaklad Fryzjerski - Jan Wojdyla");
        System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - quit / 5 - pokaz klientow");
        while(true){
            String wybor = scan.nextLine();
            switch(wybor){
                case("1") -> {
                    klienciGetInfo();
                }
                case("5") -> {
                    System.out.println("::: Lista Klientow :::");
                    for(Klient k : klienci){
                        k.showInfo();
                    }
                }
                case("help") -> {
                    System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - quit / 5 - pokaz klientow");
                }
                case("quit") -> {
                    System.out.println("Opuszczanie programu ...");
                    System.exit(0);
                }
                default -> System.out.println("Wybierz ponownie:");
            }
        }

    }
    static void klienciGetInfo(){
        System.out.println("Podaj kolejno informacje o kliencie:");
        System.out.print("Imie: ");
        String imie = scan.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scan.nextLine();
        System.out.print("Wiek: ");
        int wiek = scan.nextInt();
        scan.nextLine();
        System.out.print("Telefon: ");
        String telefon = scan.nextLine();
        Klient klient = new Klient(imie, nazwisko, wiek, telefon);
        klienci.add(klient);
        System.out.println("Klient dodany!");
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
