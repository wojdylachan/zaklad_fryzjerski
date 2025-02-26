import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;


public class ZakladFryzjerski {
    static ArrayList<Usluga> uslugi = new ArrayList<>();
    static ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();
    static ArrayList<Klient> klienci = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Zaklad Fryzjerski - Jan Wojdyla");

        dodajKlienci();
        dodajUslugi();

        System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - pokaz uslugi / 5 - pokaz klientow / quit");
        while(true){
            String wybor = scan.nextLine();
            switch(wybor) {
                case("1") -> {
                    klienciGetInfo();
                }
                case("2") -> {
                    System.out.println("Wybierz klienta poprzez nazwisko:");
                    String nazw = scan.nextLine().toLowerCase();
                    Klient klientNaRezerwacje = null;
                    int count = 0;
                    for(Klient k : klienci) {
                        if(k.getNazwisko().equalsIgnoreCase(nazw)){
                            klientNaRezerwacje = k;
                            count++;
                        }
                    }
                    if (count == 0) System.out.println("Nie ma takiego klienta");

                    System.out.println("Wybierz usluge: [pokaz uslugi]");
                    String wybor3 = scan.nextLine();
                    if(wybor3.equals("pokaz")){
                        for(Usluga u : uslugi){
                            u.showInfo();
                        }
                        System.out.println("Wybierz usluge:");
                        wybor3 = scan.nextLine();
                    }
                    Usluga wybranaUsluga = null;
                    for(Usluga u : uslugi){
                        if (u.getNazwa().equalsIgnoreCase(wybor3)){
                           wybranaUsluga = u;
                        }
                    }

                    Rezerwacja rezerwacja = new Rezerwacja(klientNaRezerwacje, wybranaUsluga);
                    rezerwacje.add(rezerwacja);
                    System.out.println("Dodano rezerwacje!");

                }
                case("4") -> {
                    System.out.println("::: Lista Uslug :::");
                    for(Usluga u : uslugi){
                        u.showInfo();
                    }
                }
                case("5") -> {
                    System.out.println("::: Lista Klientow :::");
                    for(Klient k : klienci){
                        k.showInfo();
                    }
                }
                case("3") -> {
                    System.out.println("::: Lista Klientow :::");
                    for(Rezerwacja r : rezerwacje){
                        r.showInfo();
                    }
                }
                case("help") -> {
                    System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - quit / 5 - pokaz klientow");
                }
                case("quit") -> {
                    System.out.println("Opuszczanie programu ...");
                    System.exit(0);
                }
                default -> System.out.println("Blad, wybierz ponownie:");
            }
        }

    }
    static void klienciGetInfo(){
        System.out.println("Podaj kolejno informacje o kliencie:");

        System.out.print("Imie: ");
        String imie = scan.nextLine().trim();
        while (!isAlphabetic(imie)) {
            System.out.println("Podaj ponownie imie: ");
            imie = scan.nextLine().trim();
        }
        imie = format(imie);

        System.out.print("Nazwisko: ");
        String nazwisko = scan.nextLine().trim();
        while (!isAlphabetic(nazwisko)) {
            System.out.println("Podaj ponownie nazwisko: ");
            nazwisko = scan.nextLine().trim();
        }
        nazwisko = format(nazwisko);

        System.out.print("Wiek: ");
        String wiek = scan.nextLine().trim();
        while (wiek.isEmpty() || !wiek.matches("\\d+")){
            System.out.println("Podaj ponownie wiek:");
            wiek = scan.nextLine().trim();
        } int wiek2 = Integer.parseInt(wiek);

        System.out.print("Telefon: ");
        String telefon = scan.nextLine().trim().replaceAll(" ", "");
        while(telefon.length() != 11){
            System.out.println("Podaj ponownie telefon: ");
            telefon = scan.nextLine().trim().replaceAll(" ", "");
        }


        Klient klient = new Klient(imie, nazwisko, wiek2, telefon);
        klienci.add(klient);
        System.out.println("Klient dodany!");
    }

    static void dodajKlienci(){
        Klient klient1 = new Klient("Jan", "Wojdyla", 17, "48821448632");
        Klient klient2 = new Klient("Bartosz", "Wasilkowski", 18, "48132576813");
        Klient klient3 = new Klient("Marek", "Wrona", 56, "48036953632");
        Klient klient4 = new Klient("Marta", "Kokowska", 29, "48946956130");
        klienci.add(klient1);
        klienci.add(klient2);
        klienci.add(klient3);
        klienci.add(klient4);
    }

    static void dodajUslugi(){
        Usluga usluga1 = new Usluga("Strzyżenie męskie", 50.0, 30, TypUslugi.STRZYZENIE, "Klasyczne strzyżenie męskie maszynką i nożyczkami.");
        Usluga usluga2 = new Usluga("Koloryzacja damska", 200.0, 120, TypUslugi.KOLORYZACJA, "Farbowanie włosów na wybrany kolor z użyciem profesjonalnych farb.");
        Usluga usluga3 = new Usluga("Stylizacja wieczorowa", 150.0, 60, TypUslugi.STYLIZACJA, "Elegancka fryzura na specjalne okazje.");
        uslugi.add(usluga1);
        uslugi.add(usluga2);
        uslugi.add(usluga3);
    }

    static boolean isAlphabetic(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }
        return str.matches("[a-zA-Z]+");
    }

    static String format(String str) {
        return (String.valueOf(str.charAt(0)).toUpperCase())+ (str.substring(1)).toLowerCase();
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
