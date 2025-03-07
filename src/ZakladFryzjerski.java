import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;
import java.util.*;


public class ZakladFryzjerski {
    static ArrayList<Usluga> uslugi = new ArrayList<>();
    static ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();
    static ArrayList<Klient> klienci = new ArrayList<>();
    static ArrayList<Pracownik> pracownicy = new ArrayList<>();
    static List<Product> produkty = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        dodajInfo();
        System.out.println("Zakład Fryzjerski - Jan Wojdyła");
        System.out.println("Witam w Zakładzie Fryzjerskim!");
        System.out.println("Czy jesteś klientem czy pracownikiem? [klient/pracownik]");
        String klientCzyPracownik = scan.nextLine();
        switch (klientCzyPracownik) {
            case ("klient") -> {
                System.out.println("Witaj, kliencie!");
                showOptionsK();
                while (true) {
                    System.out.print("Input: ");
                    String wybor = scan.nextLine();
                    switch (wybor) {
                        case ("1") -> {
                            klienciGetInfo();
                        }
                        case ("2") -> {
                            Klient klient = szukajKlient();
                            if (klient == null) break;
                            System.out.println("Dodano klienta!");

                            Usluga usluga = szukajUsluga();
                            if (usluga == null) break;

                            LocalTime czas = dodajCzas(usluga);
                            if (czas == null) break;

                            Rezerwacja rezerwacja = new Rezerwacja(klient, usluga, czas);
                            rezerwacje.add(rezerwacja);
                            System.out.println("Dodano rezerwacje!");
                        }
                        case ("3") -> {
                            System.out.println("::: Lista Rezerwacji :::");
                            for (Rezerwacja r : rezerwacje) {
                                r.showInfo();
                            }
                        }
                        case ("4") -> {
                            showOptionsK();
                        }
                        case ("5") -> {
                            System.out.println("Opuszczanie programu ...");
                            System.exit(0);
                        }
                    }

                }
            }
            case ("pracownik") -> {
                boolean isTrue = true;
                while (isTrue) {
                    String login1 = login();
                    switch (login1) {
                        case ("success") -> {
                            isTrue = false;
                        }
                        case ("fail") -> {
                            System.out.println("Zły email lub hasło, wpisz ponownie");
                        }
                        case ("exit") -> {
                            System.out.println("Opuszczanie programu ...");
                            System.exit(0);
                        }
                    }
                }


            }
            default -> {
                System.out.println("Brak opcji");
                System.exit(0);
            }

        }
        showOptions();
        while (true) {
            System.out.print("Input: ");
            String wybor = scan.nextLine();
            switch (wybor) {
                case ("1") -> {
                    klienciGetInfo();
                }
                case ("2") -> {
                    dodajPracownika();
                }
                case ("3") -> {
                    Klient klient = szukajKlient();
                    if (klient == null) break;
                    System.out.println("Dodano klienta!");

                    Usluga usluga = szukajUsluga();
                    if (usluga == null) break;

                    LocalTime czas = dodajCzas(usluga);
                    if (czas == null) break;

                    Rezerwacja rezerwacja = new Rezerwacja(klient, usluga, czas);
                    rezerwacje.add(rezerwacja);
                    System.out.println("Dodano rezerwacje!");
                }
                case ("4") -> {
                    System.out.println("::: Lista Rezerwacji :::");
                    for (Rezerwacja r : rezerwacje) {
                        r.showInfo();
                    }
                }
                case ("5") -> {
                    System.out.println("::: Lista Klientow :::");
                    for (Klient k : klienci) {
                        k.showInfo();
                    }
                }
                case ("6") -> {
                    System.out.println("::: Lista Uslug :::");
                    for (Usluga u : uslugi) {
                        u.showInfo();
                    }
                }
                case ("7") -> {
                    System.out.println("::: Lista Produktow :::");
                    for (Product p : produkty) {
                        p.showInfo();
                    }
                }
                case ("8") -> {
                    System.out.println("::: Lista Pracownikow :::");
                    for (Pracownik p : pracownicy) {
                        p.showInfo();
                        System.out.println(p.getHaslo());
                    }
                }
                case ("9") -> {
                    System.out.println("Dodaj Produkt / Usuń Produkt / Zaktualizuj Ilość [dodaj/usun/ilosc/exit]");
                    boolean isTrue = true;
                    while (isTrue) {
                        String wybor2 = scan.nextLine().trim().toLowerCase();
                        switch (wybor2) {
                            case ("dodaj") -> dodajProdukt();
                            case ("usun") -> usunProdukt();
                            case ("ilosc") -> aktualizujIlosc();
                            case ("exit") -> isTrue = false;
                            default -> System.out.println("Błędna opcja: wybierz ponownie");
                        }

                    }

                }
                case ("10") -> {
                    Klient klient1 = szukajKlient();
                    if (klient1 == null) break;
                    System.out.println("Szukanie informacji o kliencie ...");
                    for (Rezerwacja r : rezerwacje) {
                        if (r.klient.getImie().equalsIgnoreCase(klient1.getImie()) && r.klient.getNazwisko().equalsIgnoreCase(klient1.getNazwisko())) {
                            r.showInfo();
                        }
                    }
                }
                case ("11") -> {
                    statistics();
                }
                case ("12") -> {
                    showOptions();
                }
                case ("13") -> {
                    System.out.println("Opuszczanie programu ...");
                    System.exit(0);
                }
                default -> System.out.println("Błąd, wybierz ponownie:");
            }
        }

    }

    public static void showOptions() {
        System.out.println("1 - Dodaj Klienta");
        System.out.println("2 - Dodaj Pracownika");
        System.out.println("3 - Zrób rezerwację");
        System.out.println("4 - Zobacz rezerwacje");
        System.out.println("5 - Pokaż klientów");
        System.out.println("6 - Pokaż usługi");
        System.out.println("7 - Pokaż produkty");
        System.out.println("8 - Pokaż Pracowników");
        System.out.println("9 - Zarządzaj asortymentem");
        System.out.println("10 - Historia klienta");
        System.out.println("11 - Raport zarobków");
        System.out.println("12 - Pomoc");
        System.out.println("13 - Zakończ");
    }

    public static void showOptionsK() {
        System.out.println("1 - Dodaj Klienta");
        System.out.println("2 - Zrób rezerwację");
        System.out.println("3 - Pokaż usługi");
        System.out.println("4 - Pomoc");
        System.out.println("5 - Zakończ");
    }

    public static String login() {
        System.out.println("Podaj email");
        String email = scan.nextLine();
        if (email.equalsIgnoreCase("exit")) return "exit";
        System.out.println("Podaj haslo");
        String haslo = scan.nextLine();
        if (haslo.equalsIgnoreCase("exit")) return "exit";
        for (Pracownik p : pracownicy) {
            if (email.equals(p.getEmail()) && haslo.equals(p.getHaslo())) {
                System.out.println("Login udany!");
                return "success";
            }
        }
        return "fail";
    }


    public static void dodajProdukt() {
        System.out.println("Podaj nazwe:");
        String nazwa = scan.nextLine();
        String nazwa1 = nazwa.replaceAll(" ", "");
        while (!isAlphabetic(nazwa1)) {
            System.out.println("Podaj ponownie");
            nazwa = scan.nextLine();
            nazwa1 = nazwa.replaceAll(" ", "");
        }
        format(nazwa);

        System.out.println("Podaj cene:");
        String cena = scan.nextLine();
        String cenaTemp = cena.replaceAll("\\.", "");
        while (cenaTemp.isEmpty() || !cenaTemp.matches("\\d+")) {
            System.out.println("Podaj ponownie:");
            cenaTemp = scan.nextLine().trim();
            cenaTemp = cenaTemp.replaceAll("\\.", "");
        }
        double cena2 = Double.parseDouble(cena);

        System.out.println("Podaj ilosc");
        String ilosc = scan.nextLine();
        while (ilosc.isEmpty() || !ilosc.matches("\\d+")) {
            System.out.println("Podaj ponownie:");
            ilosc = scan.nextLine().trim();
        }
        int ilosc2 = Integer.parseInt(ilosc);

        System.out.println("Podaj kategorie:");
        String kategoria = scan.nextLine();
        while (!isAlphabetic(kategoria)) {
            System.out.println("Podaj ponownie");
            kategoria = scan.nextLine();
        }
        format(kategoria);

        produkty.add(new Product(nazwa, cena2, ilosc2, kategoria));
        System.out.println("Dodano produkt!");
    }

    public static void usunProdukt() {
        System.out.println("Wpisz nazwe produktu:");
        String wybor1 = scan.nextLine();
        for (Product p : produkty) {
            if (wybor1.equalsIgnoreCase(p.nazwa)) {
                produkty.remove(p);
                System.out.println("Produkt usuniety!");
            }
        }
    }

    public static void aktualizujIlosc() {
        System.out.println("wpisz nazwe produktu:");
        String nazwa = scan.nextLine().trim();
        System.out.println("wpisz nowa ilosc");
        int ilosc = scan.nextInt();
        scan.nextLine();

        for (Product p : produkty) {
            if (p.nazwa.equalsIgnoreCase(nazwa)) {
                p.ilosc = ilosc;
                System.out.println("Zaktualizowano ilość!");
                return;
            }
        }
        System.out.println("Produkt nie znaleziony: " + nazwa);
    }

    static void statistics() {
        System.out.println("::: Roczne statystyki :::");

        int totalMoney = 0;
        for (Rezerwacja r : rezerwacje) {
            totalMoney += r.usluga.getCena();
        }
        System.out.println("Całkowite zarobki: " + totalMoney + " zł");
        HashMap<String, Integer> salesCount = new HashMap<>();
        HashMap<String, Integer> earnings = new HashMap<>();

        for (Usluga u : uslugi) {
            int count = 0;
            int subearnings = 0;

            for (Rezerwacja r : rezerwacje) {
                if (r.usluga.getNazwa().equalsIgnoreCase(u.getNazwa())) {
                    count++;
                    subearnings += u.getCena();
                }
            }
            if (count > 0) {
                salesCount.put(u.getNazwa(), count);
                earnings.put(u.getNazwa(), subearnings);
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(salesCount.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Sprzedane usługi:");
        for (Map.Entry<String, Integer> entry : sortedList) {
            String serviceName = entry.getKey();
            System.out.println(entry.getValue() + "x - " + serviceName + " (" + earnings.get(serviceName) + " zł)");
        }
    }

    static Klient szukajKlient() {
        Klient klient = null;
        System.out.println("Wybierz klienta poprzez imie i nazwisko:");
        while (klient == null) {
            String pelneImie = scan.nextLine().toLowerCase().trim();
            String[] temp1 = pelneImie.split(" ");
            if (pelneImie.equalsIgnoreCase("exit")) {
                System.out.println("opuszczanie...");
                return null;
            } else if (!isAlphabetic(temp1[0]) || !isAlphabetic(temp1[1])) {
                System.out.println("Bledne formatowanie, podaj ponownie");
            } else if (temp1.length == 2) {
                String imie = temp1[0];
                String nazw = temp1[1];
                for (Klient k : klienci) {
                    if (k.getImie().equalsIgnoreCase(imie) && k.getNazwisko().equalsIgnoreCase(nazw)) {
                        klient = k;
                    }
                }
                if (klient == null) {
                    System.out.println("Nie ma takiego klienta, wybierz ponownie");
                }
            } else {
                System.out.println("Bledne formatowanie, podaj ponownie");
            }
        }
        return klient;
    }

    static Usluga szukajUsluga() {
        System.out.println("Wybierz usluge: [pokaz / exit]");
        Usluga usluga = null;
        while (usluga == null) {
            String wybor = scan.nextLine();
            if (wybor.equalsIgnoreCase("exit")) {
                System.out.println("opuszczanie...");
                return null;
            }
            if (wybor.equalsIgnoreCase("pokaz")) {
                System.out.println("::: Lista uslug :::");
                for (Usluga u : uslugi) {
                    u.showInfo();
                }
            } else {
                for (Usluga u : uslugi) {
                    if (u.getNazwa().equalsIgnoreCase(wybor)) {
                        usluga = u;
                    }
                }
                if (usluga != null) {
                    System.out.println("Dodano usluge!");
                } else {
                    System.out.println("Nie ma takiej uslugi, wybierz ponownie");
                }
            }

        }
        return usluga;
    }

    static LocalTime dodajCzas(Usluga usluga) {
        System.out.println("Podaj czas w formacie [xx:xx]");
        LocalTime czas = null;
        while (czas == null) {
            String wybor = scan.nextLine();
            if (wybor.equalsIgnoreCase("exit")) {
                System.out.println("Opuszczanie...");
                return null;
            }
            String[] times = wybor.split(":");
            String temp = wybor.replace(":", "");
            if ((times.length == 2) && (isNumeric(temp))) {
                int godzina = Integer.parseInt(times[0]);
                int minuty = Integer.parseInt(times[1]);
                if (godzina < 24 && minuty < 60) {
                    if (czyDostepne(godzina, minuty, usluga)) {
                        czas = LocalTime.of(godzina, minuty);
                    } else {
                        System.out.println("Nie mozna dodac, termin zajety");
                    }
                } else {
                    System.out.println("Blad formatowania - wybierz ponownie");
                }

            } else System.out.println("Bledne formatowanie - wybierz ponownie");
        }
        return czas;

    }

    static final LocalTime OPENING_TIME = LocalTime.of(9, 0);
    static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);

    static boolean czyDostepne(int godzina, int minuty, Usluga usluga) {
        LocalTime start = LocalTime.of(godzina, minuty);
        LocalTime end = start.plusMinutes(usluga.czasTrwania);
        for (Rezerwacja r : rezerwacje) {
            if (start.isBefore(r.endTime) && end.isAfter(r.startTime)) {
                return false;
            }
        }
        return true;
    }

    static void klienciGetInfo() {
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
        while (wiek.isEmpty() || !wiek.matches("\\d+")) {
            System.out.println("Podaj ponownie wiek:");
            wiek = scan.nextLine().trim();
        }
        int wiek2 = Integer.parseInt(wiek);

        System.out.print("Telefon: ");
        String telefon = scan.nextLine().trim().replaceAll(" ", "");
        while (telefon.length() != 11) {
            System.out.println("Podaj ponownie telefon: ");
            telefon = scan.nextLine().trim().replaceAll(" ", "");
        }


        Klient klient = new Klient(imie, nazwisko, wiek2, telefon);
        klienci.add(klient);
        System.out.println("Klient dodany!");
    }

    public static void dodajPracownika() {
        System.out.println("Podaj kolejno informacje o pracowniku:");

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
        while (wiek.isEmpty() || !wiek.matches("\\d+")) {
            System.out.println("Podaj ponownie wiek:");
            wiek = scan.nextLine().trim();
        }
        int wiek2 = Integer.parseInt(wiek);

        System.out.print("Telefon: ");
        String telefon = scan.nextLine().trim().replaceAll(" ", "");
        while (telefon.length() != 11) {
            System.out.println("Podaj ponownie telefon: ");
            telefon = scan.nextLine().trim().replaceAll(" ", "");
        }

        System.out.println("Email: ");
        boolean isTrue = true;
        int count = 0;
        String email = "";
        while (isTrue) {
            email = scan.nextLine().trim().replaceAll(" ", "");
            for (Pracownik p : pracownicy) {
                if (email.equalsIgnoreCase(p.getEmail())) {
                    System.out.println("Email juz istnieje, podaj inny:");
                    count++;
                }
            }
            if (count == 0) isTrue = false;
        }
        System.out.println("Haslo [wygeneruj]");

        isTrue = true;
        String haslo = "";
        while (isTrue) {
            haslo = scan.nextLine().trim().replaceAll(" ", "");
            if (haslo.equalsIgnoreCase("wygeneruj")) {
                haslo = Password.generatePassword();
                System.out.println(haslo);
                isTrue = false;
            } else if (haslo.length() < 3) {
                System.out.println("Za krotkie haslo, podaj ponownie:");
            } else isTrue = false;
        }
        List<TypUslugi> listaUslug = new ArrayList<>();
        System.out.println("Dostępne usługi:");
        for (TypUslugi usluga : TypUslugi.values()) {
            System.out.println("- " + usluga);
        }
        System.out.println("Podaj wybrane usługi, oddzielając je przecinkami:");
        String input = scan.nextLine();
        String[] wybraneUslugi = input.split(",");
        for (String nazwaUslugi : wybraneUslugi) {
            try {
                TypUslugi usluga = TypUslugi.valueOf(nazwaUslugi.trim().toUpperCase());
                listaUslug.add(usluga);
            } catch (IllegalArgumentException e) {
                System.out.println("Nieznana usługa: " + nazwaUslugi.trim());
            }
        }

        pracownicy.add(new Pracownik(imie, nazwisko, wiek2, telefon, email, haslo, listaUslug));
        System.out.println("pracownik dodany!");


    }

    static boolean isAlphabetic(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }
        return str.matches("[a-zA-Z]+");
    }

    static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) != true) return false;
        }
        return true;
    }

    static String format(String str) {
        return (String.valueOf(str.charAt(0)).toUpperCase()) + (str.substring(1)).toLowerCase();
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
        uslugi.add(usluga10);
        uslugi.add(usluga10);


        rezerwacje.add(new Rezerwacja(klient1, usluga7, LocalTime.of(9, 0)));
//        rezerwacje.add(new Rezerwacja(klient2, usluga2, LocalTime.of(10, 0)));
//        rezerwacje.add(new Rezerwacja(klient3, usluga5, LocalTime.of(12, 0)));
//        rezerwacje.add(new Rezerwacja(klient4, usluga3, LocalTime.of(13, 30)));
        rezerwacje.add(new Rezerwacja(klient5, usluga1, LocalTime.of(15, 0)));
//        rezerwacje.add(new Rezerwacja(klient6, usluga6, LocalTime.of(16, 30)));
//        rezerwacje.add(new Rezerwacja(klient7, usluga9, LocalTime.of(17, 30)));
//        rezerwacje.add(new Rezerwacja(klient8, usluga10, LocalTime.of(18, 0)));
        rezerwacje.add(new Rezerwacja(klient9, usluga8, LocalTime.of(19, 30)));
        List<TypUslugi> l = new ArrayList<TypUslugi>();
        l.add(TypUslugi.STRZYZENIE);
        List<TypUslugi> l2 = new ArrayList<TypUslugi>();
        l2.add(TypUslugi.KOLORYZACJA);
        l2.add(TypUslugi.STYLIZACJA);
        pracownicy.add(new Pracownik("Franek", "Pietrowicz", 53, "48537605268", "franekpietrowicz72@gmail.com", "%Aarc&eo0", l));
        pracownicy.add(new Pracownik("Katarzyna", "Pawlak", 27, "48666621994", "katarzynapawlak98@gmail.com", "lk#Dt3Y9sjY", l2));
        pracownicy.add(new Pracownik("Zofia", "Rutkowska", 33, "48781477302", "zofiarutkowska92@gmail.com", "87TI*&r2mO", l2));
        produkty.add(new Product("Szampon regenerujący", 45.99, 10, "Szampon"));
        produkty.add(new Product("Odżywka nawilżająca", 39.99, 15, "Odżywka"));
        produkty.add(new Product("Lakier do włosów", 29.99, 20, "Stylizacja"));
        produkty.add(new Product("Żel do włosów", 19.99, 25, "Stylizacja"));
        produkty.add(new Product("Maska keratynowa", 59.99, 8, "Maska"));
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
