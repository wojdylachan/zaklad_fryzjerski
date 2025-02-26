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
            switch(wybor) {
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
