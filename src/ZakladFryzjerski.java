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

        dodajInfo();

        System.out.println("1 - Dodaj Klienta / 2 - Zrob rezerwacje / 3 - zobacz rezerwacje / 4 - pokaz uslugi / 5 - pokaz klientow / quit");
        while(true){
            System.out.print("Input: ");
            String wybor = scan.nextLine();
            switch(wybor) {
                case("1") -> {
                    klienciGetInfo();
                }
                case("2") -> {
                    Klient klient = szukajKlient();
                    if(klient == null) break;

                    Usluga usluga = szukajUsluga();
                    if(usluga == null) break;

                    LocalTime czas = dodajCzas(usluga);
                    if(czas == null) break;

                    Rezerwacja rezerwacja = new Rezerwacja(klient, usluga, czas);
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
                    System.out.println("::: Lista Rezerwacji :::");
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


    static Klient szukajKlient(){
        Klient klient = null;
        System.out.println("Wybierz klienta poprzez imie i nazwisko:");
        while (klient == null) {
            String pelneImie = scan.nextLine().toLowerCase().trim();
            String[] temp1 = pelneImie.split(" ");
            if (pelneImie.equalsIgnoreCase("exit")) {
                System.out.println("opuszczanie...");
                return null;
            } else if (!isAlphabetic(temp1[0]) || !isAlphabetic(temp1[1])){
                System.out.println("Bledne formatowanie, podaj ponownie");
            }
            else if (temp1.length == 2) {
                String imie = temp1[0];
                String nazw = temp1[1];
                for (Klient k : klienci) {
                    if (k.getImie().equalsIgnoreCase(imie) && k.getNazwisko().equalsIgnoreCase(nazw)) {
                        klient = k;
                    }
                }
                if (klient != null) {
                    System.out.println("Dodano klienta!");
                } else {
                    System.out.println("Nie ma takiego klienta, wybierz ponownie");
                }
            } else {
                System.out.println("Bledne formatowanie, podaj ponownie");
            }


        } return klient;
    }

    static Usluga szukajUsluga(){
        System.out.println("Wybierz usluge: [pokaz / exit]");
        Usluga usluga = null;
        while(usluga == null){
            String wybor = scan.nextLine();
            if (wybor.equalsIgnoreCase("exit")){
                System.out.println("opuszczanie...");
                return null;
            }
            if(wybor.equalsIgnoreCase("pokaz")) {
                System.out.println("::: Lista uslug :::");
                for(Usluga u : uslugi){
                    u.showInfo();
                }
            }
            else {
                for(Usluga u : uslugi) {
                    if (u.getNazwa().equalsIgnoreCase(wybor)){
                        usluga = u;
                    }
                }
                if (usluga != null) {
                    System.out.println("Dodano usluge!");
                } else {
                    System.out.println("Nie ma takiej uslugi, wybierz ponownie");
                }
            }

        } return usluga;
    }

    static LocalTime dodajCzas(Usluga usluga){
        System.out.println("Podaj czas w formacie [XX:XX]");
        LocalTime czas = null;
        while(czas == null){
            String wybor = scan.nextLine();
            if (wybor.equalsIgnoreCase("exit")){
                System.out.println("Opuszczanie...");
                return null;
            }
            String[] times = wybor.split(":");
            if (times.length == 2) {
                int godzina = Integer.parseInt(times[0]);
                int minuty = Integer.parseInt(times[1]);
                if (godzina >= 24 || minuty >= 60) System.out.println("Blad formatowania");
                if (czyDostepne(godzina, minuty, usluga)){
                    czas = LocalTime.of(godzina, minuty);
                } else {
                    System.out.println("Nie mozna dodac, termin zajety");
                }
            } else System.out.println("Bledne formatowanie - wybierz ponownie");
        } return czas;

    }
    static boolean czyDostepne(int godzina, int minuty, Usluga usluga){
        LocalTime start = LocalTime.of(godzina, minuty);
        LocalTime end = start.plusMinutes(usluga.czasTrwania);
        for(Rezerwacja r : rezerwacje) {
            if (start.isBefore(r.endTime) && end.isAfter(r.startTime)){
                return false;
            }
        }
        return true;
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

    static void dodajInfo() {
        Klient klient1 = new Klient("Jan", "Wojdyla", 17, "48821448632");
        Klient klient2 = new Klient("Bartosz", "Wasilkowski", 18, "48132576813");
        Klient klient3 = new Klient("Marek", "Wrona", 56, "48036953632");
        Klient klient4 = new Klient("Marta", "Kokowska", 29, "48946956130");
        Klient klient5 = new Klient("Anna", "Nowak", 35, "48765432109");
        Klient klient6 = new Klient("Piotr", "Lis", 42, "48987654321");
        Klient klient7 = new Klient("Katarzyna", "Zawadzka", 25, "48123456789");
        Klient klient8 = new Klient("Dominik", "Majewski", 31, "48234567890");
        Klient klient9 = new Klient("Natalia", "Bielecka", 27, "48345678901");
        Klient klient10 = new Klient("Szymon", "Jankowski", 45, "48456789012");

        klienci.add(klient1);
        klienci.add(klient2);
        klienci.add(klient3);
        klienci.add(klient4);
        klienci.add(klient5);
        klienci.add(klient6);
        klienci.add(klient7);
        klienci.add(klient8);
        klienci.add(klient9);
        klienci.add(klient10);


        Usluga usluga1 = new Usluga("Strzyżenie męskie", 50.0, 30, TypUslugi.STRZYZENIE, "Klasyczne strzyżenie męskie maszynką i nożyczkami.");
        Usluga usluga2 = new Usluga("Koloryzacja damska", 200.0, 120, TypUslugi.KOLORYZACJA, "Farbowanie włosów na wybrany kolor z użyciem profesjonalnych farb.");
        Usluga usluga3 = new Usluga("Stylizacja wieczorowa", 150.0, 60, TypUslugi.STYLIZACJA, "Elegancka fryzura na specjalne okazje.");
        Usluga usluga4 = new Usluga("Strzyżenie damskie", 70.0, 45, TypUslugi.STRZYZENIE, "Precyzyjne strzyżenie damskie zgodnie z najnowszymi trendami.");
        Usluga usluga5 = new Usluga("Balayage", 250.0, 150, TypUslugi.KOLORYZACJA, "Naturalne przejścia kolorystyczne w stylu balayage.");
        Usluga usluga6 = new Usluga("Modelowanie włosów", 80.0, 40, TypUslugi.STYLIZACJA, "Profesjonalne modelowanie i układanie włosów.");
        Usluga usluga7 = new Usluga("Strzyżenie brody", 40.0, 20, TypUslugi.STRZYZENIE, "Precyzyjne strzyżenie i konturowanie brody.");
        Usluga usluga8 = new Usluga("Ombre", 220.0, 130, TypUslugi.KOLORYZACJA, "Modna koloryzacja ombre z łagodnym przejściem kolorów.");
        Usluga usluga9 = new Usluga("Upięcie ślubne", 300.0, 90, TypUslugi.STYLIZACJA, "Specjalna fryzura ślubna dostosowana do preferencji panny młodej.");
        Usluga usluga10 = new Usluga("Strzyżenie dziecięce", 35.0, 25, TypUslugi.STRZYZENIE, "Delikatne i bezstresowe strzyżenie dziecięce.");

        uslugi.add(usluga1);
        uslugi.add(usluga2);
        uslugi.add(usluga3);
        uslugi.add(usluga4);
        uslugi.add(usluga5);
        uslugi.add(usluga6);
        uslugi.add(usluga7);
        uslugi.add(usluga8);
        uslugi.add(usluga9);
        uslugi.add(usluga10);

        rezerwacje.add(new Rezerwacja(klient1, usluga7, LocalTime.of(9, 0)));
        rezerwacje.add(new Rezerwacja(klient2, usluga2, LocalTime.of(10, 0)));
        rezerwacje.add(new Rezerwacja(klient3, usluga5, LocalTime.of(12, 0)));
//        rezerwacje.add(new Rezerwacja(klient4, usluga3, LocalTime.of(13, 30)));
//        rezerwacje.add(new Rezerwacja(klient5, usluga1, LocalTime.of(15, 0)));
        rezerwacje.add(new Rezerwacja(klient6, usluga6, LocalTime.of(16, 30)));
        rezerwacje.add(new Rezerwacja(klient7, usluga9, LocalTime.of(17, 30)));
//        rezerwacje.add(new Rezerwacja(klient8, usluga10, LocalTime.of(18, 0)));
//        rezerwacje.add(new Rezerwacja(klient9, usluga8, LocalTime.of(19, 30)));
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
