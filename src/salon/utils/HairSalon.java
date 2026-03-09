package salon.utils;

import salon.logic.Product;
import salon.enums.ServiceType;
import salon.logic.Reservation;
import salon.logic.Service;
import salon.model.Client;
import salon.model.Employee;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;
import java.util.*;


public class HairSalon {
    static ArrayList<Service> services = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        addInfo();
        System.out.println("Zakład Fryzjerski - Jan Wojdyła");
        System.out.println("Witam w Zakładzie Fryzjerskim!");
        System.out.println("Czy jesteś klientem czy pracownikiem?");
        System.out.println("1 - Klient");
        System.out.println("2 - Pracownik");
        System.out.print("Input: ");
        String personInput = scanner.nextLine();
        switch (personInput) {
            case ("1") -> {
                System.out.println("Witaj, kliencie!");
                while (true) {
                    showOptionsClient();
                    System.out.print("Input: ");
                    String choice = scanner.nextLine().toLowerCase();
                    switch (choice) {
                        case ("1") -> {
                            addClient();
                        }
                        case ("2") -> {
                            Client client = findClient();
                            if (client == null) break;
                            System.out.println("Klient został dodany! \n");

                            Service service = findService();
                            if (service == null) break;

                            Employee employee = findEmployee(service);
                            if (employee == null) break;
                            System.out.println("Pracownik został dodany! \n");

                            LocalDateTime time = requestDateTime(service, employee);
                            if (time == null) break;

                            Reservation reservation = new Reservation(client, employee, service, time);
                            reservations.add(reservation);
                            System.out.println("Dodano rezerwacje!");
                        }
                        case ("3") -> {
                            System.out.println("::: Lista Rezerwacji :::");
                            displayReservations();
                        }
                        case ("4"), ("pomoc"), ("menu") -> {
                            showOptionsClient();
                        }
                        case ("5"), ("zakoncz"), ("zakończ"), ("exit") -> {
                            System.out.println("Opuszczanie programu ...");
                            System.exit(0);
                        }
                    }
                }
            }
            case ("2") -> {
                while (true) {
                    String login1 = employeeLogin();
                    switch (login1) {
                        case ("success") -> {
                            while (true) {
                                showOptionsEmployee();
                                System.out.print("Input: ");
                                String choice = scanner.nextLine();
                                switch (choice) {
                                    case ("1") -> {
                                        addClient();
                                    }
                                    case ("2") -> {
                                        addEmployee();
                                    }
                                    case ("3") -> {
                                        Client client = findClient();
                                        if (client == null) break;
                                        System.out.println("Dodano klienta!");


                                        Service service = findService();
                                        if (service == null) break;

                                        Employee employee = findEmployee(service);
                                        if (employee == null) break;

                                        LocalDateTime time = requestDateTime(service, employee);
                                        if (time == null) break;

                                        Reservation reservation = new Reservation(client, employee, service, time);
                                        reservations.add(reservation);
                                        System.out.println("Dodano rezerwacje!");
                                    }
                                    case ("4") -> {
                                        System.out.println("::: Lista Rezerwacji :::");
                                        displayReservations();
                                    }
                                    case ("5") -> {
                                        System.out.println("::: Lista Klientow :::");
                                        for (Client c : clients) {
                                            c.showInfo();
                                        }
                                        System.out.println("");
                                    }
                                    case ("6") -> {
                                        System.out.println("::: Lista Produktow :::");
                                        for (Product p : products) {
                                            p.showInfo();
                                        }
                                        System.out.println("");
                                    }
                                    case ("7") -> {
                                        System.out.println("::: Lista Pracownikow :::");
                                        for (Employee e : employees) {
                                            e.showInfoFull();
                                        }
                                        System.out.println("");
                                    }
                                    case ("8") -> {
                                        boolean isTrue = true;
                                        while (isTrue) {
                                            showOptionsProducts();
                                            System.out.println("Input:");
                                            String choice2 = scanner.nextLine().trim().toLowerCase();
                                            switch (choice2) {
                                                case ("1"), ("dodaj") -> addProduct();
                                                case ("2"), ("usun") -> removeProduct();
                                                case ("3"), ("ilosc") -> updateStock();
                                                case ("4"), ("pokaz") -> {
                                                    System.out.println("::: Lista Produktow :::");
                                                    for (Product p : products) {
                                                        p.showInfo();
                                                    }
                                                    System.out.println("");
                                                }
                                                case ("5"), ("exit") -> isTrue = false;
                                                default -> System.out.println("Błędna opcja: wybierz ponownie");
                                            }

                                        }

                                    }
                                    case ("9") -> {
                                        Client client = findClient();
                                        if (client == null) break;
                                        System.out.println("Szukanie informacji o kliencie ...");
                                        for (Reservation r : reservations) {
                                            if (r.getClient().getFirstName().equalsIgnoreCase(client.getFirstName()) && r.getClient().getLastName().equalsIgnoreCase(client.getLastName())) {
                                                r.showInfo();
                                            }
                                        }
                                    }
                                    case ("10") -> {
                                        statistics();
                                    }
                                    case ("11"), ("pomoc"), ("menu") -> {
                                        showOptionsEmployee();
                                    }
                                    case ("12"), ("zakoncz"), ("zakończ"), ("exit") -> {
                                        System.out.println("Opuszczanie programu ...");
                                        System.exit(0);
                                    }
                                    default -> System.out.println("Błąd, wybierz ponownie:");
                                }
                            }
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
    }
    public static void showOptionsEmployee() {
        System.out.println("\n=== MENU PRACOWNIKA ===");
        System.out.println("1. Dodaj Klienta");
        System.out.println("2. Dodaj Pracownika");
        System.out.println("3. Zrób rezerwację");
        System.out.println("4. Zobacz rezerwacje");
        System.out.println("5. Pokaż klientów");
        System.out.println("6. Pokaż produkty");
        System.out.println("7. Pokaż Pracowników");
        System.out.println("8. Zarządzaj asortymentem");
        System.out.println("9. Historia klienta");
        System.out.println("10. Raport zarobków");
        System.out.println("11. Pomoc");
        System.out.println("12. Zakończ");
    }

    public static void showOptionsClient() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Dodaj Klienta");
        System.out.println("2. Zrób rezerwację");
        System.out.println("3. Pokaż usługi");
        System.out.println("4. Pomoc");
        System.out.println("5. Zakończ");
    }

    public static void showOptionsProducts() {
        System.out.println("=== PRODUKTY MENU ===");
        System.out.println("1 - Dodaj Produkt");
        System.out.println("2 - Usuń Produkt");
        System.out.println("3 - Zaktualizuj Ilość");
        System.out.println("4 - Pokaż listę produktów");
        System.out.println("5 - Opusc Program");
    }

    public static String employeeLogin() {
        System.out.println("Podaj email:");
        String email = scanner.nextLine();
        if (email.equalsIgnoreCase("exit")) return "exit";
        System.out.println("Podaj haslo");
        String password = scanner.nextLine();
        if (password.equalsIgnoreCase("exit")) return "exit";
        for (Employee c : employees) {
            if (email.equals(c.getEmail()) && password.equals(c.getPassword())) {
                System.out.println("Login udany!");
                return "success";
            }
        }
        return "fail";
    }


    public static void addProduct() {
        System.out.println("Podaj nazwę produktu:");
        String name1 = scanner.nextLine();
        name1 = name1.replaceAll(" ", "");
        while (!isAlphabetic(name1)) {
            System.out.println("Źle podane dane, podaj ponownie:");
            name1 = scanner.nextLine();
            name1 = name1.replaceAll(" ", "");
        }
        format(name1);

        System.out.println("Podaj cenę [zł]:");
        String price = scanner.nextLine();
        String priceTemp = price.replaceAll("\\.", "");
        while (priceTemp.isEmpty() || !priceTemp.matches("\\d+")) {
            System.out.println("Źle podane dane, podaj ponownie:");
            priceTemp = scanner.nextLine().trim();
            priceTemp = priceTemp.replaceAll("\\.", "");
        }
        double price2 = Double.parseDouble(price);

        System.out.println("Podaj ilość:");
        String amount = scanner.nextLine();
        while (amount.isEmpty() || !amount.matches("\\d+")) {
            System.out.println("Źle podane dane, podaj ponownie:");
            amount = scanner.nextLine().trim();
        }
        int amount2 = Integer.parseInt(amount);

        System.out.println("Podaj kategorie:");
        for (ServiceType s : ServiceType.values()) {
            System.out.print("[" + s + "] ");
        }
        String category = scanner.nextLine();
        while (!isAlphabetic(category)) {
            System.out.println("Źle podane dane, podaj ponownie:");
            category = scanner.nextLine();
        }
        format(category);




        products.add(new Product(name1, price2, amount2, category));
        System.out.println("Dodano produkt!");
    }

    public static void removeProduct() {
        System.out.print("Podaj nazwę produktu do usunięcia: ");
        String nameToRemove = scanner.nextLine().trim();

        Iterator<Product> it = products.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Product p = it.next();
            if (p.getName().equalsIgnoreCase(nameToRemove)) {
                it.remove();
                found = true;
                System.out.println("Usunięto " + p.getName() + ".");
            }
        }

        if (!found) {
            System.out.println("Nie znaleziono produktu");
        }
    }

    private static int checkInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                if (input.isEmpty()) {
                    System.out.println("Input nie może byc pusty");
                    continue;
                }
                int value = Integer.parseInt(input);

                if (value < 0) {
                    System.out.println("Wartość nie może być ujemna.");
                    continue;
                }
                return value;

            } catch (NumberFormatException e) {
                System.out.println("Proszę podać pełną liczbę:");
            }
        }
    }

    public static void updateStock() {
        System.out.print("Podaj nazwę produktu do zaktualizowania: ");
        String name = scanner.nextLine().trim();

        Product foundProduct = null;
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct == null) {
            System.out.println("Nie znaleziono produktu.");
            return;
        }

        System.out.println("Znaleziono: " + foundProduct.getName() + " (Current stock: " + foundProduct.getAmount() + ")");
        int newAmount = checkInt("Podaj nową ilość: ");

        foundProduct.setAmount(newAmount);
        System.out.println("Nows ilość produktu: " + newAmount);
    }

    static void statistics() {
        System.out.println("::: Roczne statystyki :::");

        int totalMoney = 0;
        for (Reservation r : reservations) {
            totalMoney += r.getService().getPrice();
        }
        HashMap<String, Integer> salesCount = new HashMap<>();
        HashMap<String, Integer> earnings = new HashMap<>();

        for (Service s : services) {
            int count = 0;
            int subearnings = 0;

            for (Reservation r : reservations) {
                if (r.getService().getName().equalsIgnoreCase(s.getName())) {
                    count++;
                    subearnings += s.getPrice();
                }
            }
            if (count > 0) {
                salesCount.put(s.getName(), count);
                earnings.put(s.getName(), subearnings);
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(salesCount.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Sprzedane usługi:");
        for (Map.Entry<String, Integer> entry : sortedList) {
            String serviceName = entry.getKey();
            System.out.println(entry.getValue() + "x - " + serviceName + " (" + earnings.get(serviceName) + " zł)");
        }
        System.out.println("Całkowite zarobki: " + totalMoney + " zł\n");
    }


    private static Client findClient() {
        Client foundClient = null;
        System.out.println("Wybierz klienta poprzez imie i nazwisko: [exit]");

        while (foundClient == null) {
            System.out.print("Input: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("opuszczanie...");
                return null;
            }

            String[] nameParts = input.split("\\s+");

            if (nameParts.length != 2) {
                System.out.println("Błąd: Podaj imię i nazwisko osobno.");
                continue;
            }

            if (!isAlphabetic(nameParts[0]) || !isAlphabetic(nameParts[1])) {
                System.out.println("Bledne formatowanie, podaj ponownie:");
                continue;
            }

            String firstName = nameParts[0];
            String lastName = nameParts[1];

            for (Client c : clients) {
                if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)) {
                    foundClient = c;
                    break;
                }
            }

            if (foundClient == null) {
                System.out.println("Nie znaleziono takiego klienta, wybierz ponownie:");
            }
        }

        System.out.println("Wybrany klient: " + foundClient.getFullName());
        return foundClient;
    }


    static Employee findEmployee(Service selectedService) {
        Employee foundEmployee = null;
        System.out.println("Usługa: " + selectedService.getName() + " [" + selectedService.getServiceType() + "]");
        System.out.println("\n Wyszukaj pracownika według wzoru [Imię Nazwisko]:");
        System.out.println("1 - wyświetl listę wszystkich pracowników");
        System.out.println("2 - wyświetl tylko specjalistów od tej usługi");
        System.out.println("3 - anuluj i wróć");

        while (foundEmployee == null) {
            System.out.print("Input: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("3") || input.equalsIgnoreCase("exit")) {
                System.out.println("Anulowanie wyszukiwania...");
                return null;
            }

            if (input.equals("1")) {
                System.out.println("::: WSZYSCY PRACOWNICY :::");
                employees.forEach(Employee::showInfo);
                continue;
            }

            if (input.equals("2")) {
                System.out.println("::: SPECJALIŚCI DLA: " + selectedService.getServiceType() + " :::");
                for (Employee e : employees) {
                    if (e.getServiceType().contains(selectedService.getServiceType())) {
                        e.showInfo();
                    }
                }
                continue;
            }

            String[] nameParts = input.split(" ");
            if (nameParts.length == 2 && isAlphabetic(nameParts[0]) && isAlphabetic(nameParts[1])) {
                String firstName = nameParts[0];
                String lastName = nameParts[1];

                for (Employee e : employees) {
                    if (e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName)) {

                        if (e.getServiceType().contains(selectedService.getServiceType())) {
                            foundEmployee = e;
                            System.out.println("Wybrano pracownika: " + e.getFullName());
                            break;
                        } else {
                            System.out.println("błąd: " + e.getFullName() + " nie wykonuje usługi: " + selectedService.getServiceType());
                            System.out.println("Wybierz innego pracownika.");
                        }
                    }
                }
            } else {
                System.out.println("Błędny format. Wpisz [Imię Nazwisko] lub wybierz opcję z menu.");
            }
        }
        return foundEmployee;
    }

    static Service findService() {
        System.out.println("Wybierz usluge: ");
        System.out.println("1 - wyświetl listę usług");
        System.out.println("2 - opuść program");

        Service service = null;
        while (service == null) {
            System.out.print("Input: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("exit")) {
                System.out.println("opuszczanie...");
                return null;
            }
            if (input.equalsIgnoreCase("1")) {
                System.out.println("::: Lista uslug :::");
                for (Service s : services) {
                    s.showInfo();
                }
            } else {
                for (Service s : services) {
                    if (s.getName().equalsIgnoreCase(input)) {
                        service = s;
                    }
                }
                if (service != null) {
                    System.out.println("Dodano usluge!");
                } else {
                    System.out.println("Taka usługa nie istnieje, wybierz ponownie: ");
                }
            }

        }
        return service;
    }

    private static final LocalTime OPENING_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);

    public static LocalDateTime requestDateTime(Service service, Employee employee) {
        while (true) {
            try {
                System.out.println("Podaj datę (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                if (dateInput.equalsIgnoreCase("exit")) return null;

                System.out.println("Podaj czas (HH:mm): ");
                String timeInput = scanner.nextLine();

                String dateTimeStr = dateInput + "T" + timeInput;
                LocalDateTime selectedTime = LocalDateTime.parse(dateTimeStr);

                if (selectedTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Błąd: podano czas w przeszłości.");
                    continue;
                }

                if (isAvailable(selectedTime, service, employee)) {
                    return selectedTime;
                } else {
                    System.out.println("Błąd: To miejsce jest zajęte przez " + employee.getFullName() + " lub salon jest zamknięty.");
                }
            } catch (Exception e) {
                System.out.println("Błędne formatowanie! Proszę podać datę i czas w formacie [YYYY-MM-DD] i [HH:mm].");
            }
        }
    }

    public static boolean isAvailable(LocalDateTime start, Service service, Employee employee) {
        LocalDateTime end = start.plusMinutes(service.getDuration());

        if (start.toLocalTime().isBefore(OPENING_TIME) || end.toLocalTime().isAfter(CLOSING_TIME)) {
            return false;
        }

        for (Reservation r : reservations) {
            if (r.getEmployee().equals(employee)) {
                if (start.isBefore(r.getEndTime()) && end.isAfter(r.getStartTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    static void addClient() {
        System.out.println("Podaj kolejno informacje o kliencie:");

        System.out.print("Imie: ");
        String firstName = scanner.nextLine().trim();
        while (!isAlphabetic(firstName)) {
            System.out.println("Podaj ponownie imie: ");
            firstName = scanner.nextLine().trim();
        }
        firstName = format(firstName);

        System.out.print("Nazwisko: ");
        String lastName = scanner.nextLine().trim();
        while (!isAlphabetic(lastName)) {
            System.out.println("Podaj ponownie nazwisko: ");
            lastName = scanner.nextLine().trim();
        }
        lastName = format(lastName);

        System.out.print("Wiek: ");
        String age = scanner.nextLine().trim();
        while (age.isEmpty() || !age.matches("\\d+")) {
            System.out.println("Proszę podaj wiek w postaci liczby:");
            age = scanner.nextLine().trim();
        }
        int age1 = Integer.parseInt(age);

        System.out.print("Telefon: ");
        String telephone = scanner.nextLine().trim().replaceAll(" ", "");
        while (telephone.length() != 11) {
            System.out.println("Numer telefonu musi mieć dokładnie 11 znaków. Podaj ponownie: ");
            telephone = scanner.nextLine().trim().replaceAll(" ", "");
        }

        clients.add(new Client(firstName, lastName, age1, telephone));
        System.out.println("Klient dodany!");
    }

    public static void addEmployee() {
        System.out.println("Podaj kolejno informacje o pracowniku:");

        System.out.print("Imie: ");
        String name = scanner.nextLine().trim();
        while (!isAlphabetic(name)) {
            System.out.println("Podaj ponownie name: ");
            name = scanner.nextLine().trim();
        }
        name = format(name);

        System.out.print("Nazwisko: ");
        String lastName = scanner.nextLine().trim();
        while (!isAlphabetic(lastName)) {
            System.out.println("Podaj ponownie nazwisko: ");
            lastName = scanner.nextLine().trim();
        }
        lastName = format(lastName);

        System.out.print("Wiek: ");
        String age = scanner.nextLine().trim();
        while (age.isEmpty() || !age.matches("\\d+")) {
            System.out.println("Podaj ponownie age:");
            age = scanner.nextLine().trim();
        }
        int age1 = Integer.parseInt(age);

        System.out.print("Telefon: ");
        String telephone = scanner.nextLine().trim().replaceAll(" ", "");
        while (telephone.length() != 11) {
            System.out.println("Numer telefonu musi mieć dokładnie 11 znaków. Podaj ponownie: ");
            telephone = scanner.nextLine().trim().replaceAll(" ", "");
        }

        System.out.println("Email: ");
        boolean isTrue = true;
        int count = 0;
        String email = "";
        while (isTrue) {
            email = scanner.nextLine().trim().replaceAll(" ", "");
            for (Employee p : employees) {
                if (email.equalsIgnoreCase(p.getEmail())) {
                    System.out.println("Email juz istnieje, podaj inny:");
                    count++;
                }
            }
            if (count == 0) isTrue = false;
        }
        System.out.println("Stwórz hasło lub wpisz [wygeneruj] aby automatycznie wygenerować:");

        isTrue = true;
        String password = "";
        while (isTrue) {
            password = scanner.nextLine().trim().replaceAll(" ", "");
            if (password.equalsIgnoreCase("wygeneruj")) {
                password = Password.generatePassword();
                System.out.println(password);
                isTrue = false;
            } else if (password.length() < 5) {
                System.out.println("Za krotkie haslo, podaj ponownie:");
            } else isTrue = false;
        }
        List<ServiceType> serviceTypeList = new ArrayList<>();
        System.out.println("Dostępne usługi:");
        for (ServiceType service : ServiceType.values()) {
            System.out.println("- " + service);
        }
        System.out.println("Podaj wybrane usługi, oddzielając je przecinkami:");
        String input = scanner.nextLine();
        String[] chosenServices = input.split(",");
        for (String s : chosenServices) {
            try {
                ServiceType service = ServiceType.valueOf(s.trim().toUpperCase());
                serviceTypeList.add(service);
            } catch (IllegalArgumentException e) {
                System.out.println("Nieznana usługa: " + s.trim());
            }
        }

        employees.add(new Employee(name, lastName, age1, telephone, email, password, serviceTypeList));
        System.out.println("pracownik dodany!");


    }

    public static void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Nie znaleziono rezerwacji");
            return;
        }

        reservations.sort(Comparator.comparing(Reservation::getStartTime));

        System.out.println("\n===== ROZKŁAD =====");

        LocalDate lastDate = null;
        DateTimeFormatter dateHeaderFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Reservation b : reservations) {
            LocalDate currentDate = b.getStartTime().toLocalDate();

            if (lastDate == null || !currentDate.isEqual(lastDate)) {
                System.out.println("\n--- DATA: " + currentDate.format(dateHeaderFormatter).toUpperCase() + " ---");
                lastDate = currentDate;
            }

            System.out.printf("   [%s - %s] %-15s | Klient: %-15s | Pracownicy: %-15s%n",
                    b.getStartTime().format(timeFormatter),
                    b.getEndTime().toLocalTime().format(timeFormatter),
                    b.getService().getName(),
                    b.getClient().getFullName(),
                    b.getEmployee().getFullName()
            );
        }
        System.out.println("");
    }

    static boolean isAlphabetic(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }
        return str.matches("[a-zA-Z]+");
    }

    static String format(String str) {
        return (String.valueOf(str.charAt(0)).toUpperCase()) + (str.substring(1)).toLowerCase();
    }

    static void addInfo() {
        Client client1 = new Client("Jan", "Wojdyla", 17, "48821448632");
        Client client2 = new Client("Bartosz", "Wasilkowski", 18, "48132576813");
        Client client3 = new Client("Marek", "Wrona", 56, "48036953632");
        Client client4 = new Client("Marta", "Kokowska", 29, "48946956130");
        Client client5 = new Client("Anna", "Nowak", 35, "48765432109");
        Client client6 = new Client("Piotr", "Lis", 42, "48987654321");
        Client client7 = new Client("Katarzyna", "Zawadzka", 25, "48123456789");
        Client client8 = new Client("Dominik", "Majewski", 31, "48234567890");
        Client client9 = new Client("Natalia", "Bielecka", 27, "48345678901");
        Client client10 = new Client("Szymon", "Jankowski", 45, "48456789012");

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        clients.add(client6);
        clients.add(client7);
        clients.add(client8);
        clients.add(client9);
        clients.add(client10);


        Service service1 = new Service("Strzyżenie męskie", 50.0, 30, ServiceType.HAIRCUT, "Klasyczne strzyżenie męskie maszynką i nożyczkami.");
        Service service2 = new Service("Koloryzacja damska", 200.0, 120, ServiceType.COLORING, "Farbowanie włosów na wybrany kolor z użyciem profesjonalnych farb.");
        Service service3 = new Service("Stylizacja wieczorowa", 150.0, 60, ServiceType.STYLING, "Elegancka fryzura na specjalne okazje.");
        Service service4 = new Service("Strzyżenie damskie", 70.0, 45, ServiceType.HAIRCUT, "Precyzyjne strzyżenie damskie zgodnie z najnowszymi trendami.");
        Service service5 = new Service("Balayage", 250.0, 150, ServiceType.COLORING, "Naturalne przejścia kolorystyczne w stylu balayage.");
        Service service6 = new Service("Modelowanie włosów", 80.0, 40, ServiceType.STYLING, "Profesjonalne modelowanie i układanie włosów.");
        Service service7 = new Service("Strzyżenie brody", 40.0, 20, ServiceType.HAIRCUT, "Precyzyjne strzyżenie i konturowanie brody.");
        Service service8 = new Service("Ombre", 220.0, 130, ServiceType.COLORING, "Modna koloryzacja ombre z łagodnym przejściem kolorów.");
        Service service9 = new Service("Upięcie ślubne", 300.0, 90, ServiceType.STYLING, "Specjalna fryzura ślubna dostosowana do preferencji panny młodej.");
        Service service10 = new Service("Strzyżenie dziecięce", 35.0, 25, ServiceType.HAIRCUT, "Delikatne i bezstresowe strzyżenie dziecięce.");

        services.add(service1);
        services.add(service2);
        services.add(service3);
        services.add(service4);
        services.add(service5);
        services.add(service6);
        services.add(service7);
        services.add(service8);
        services.add(service9);
        services.add(service10);
        services.add(service10);
        services.add(service10);


        List<ServiceType> serviceList1 = new ArrayList<ServiceType>();
        serviceList1.add(ServiceType.HAIRCUT);
        List<ServiceType> serviceList2 = new ArrayList<ServiceType>();
        serviceList2.add(ServiceType.COLORING);
        serviceList2.add(ServiceType.STYLING);
        employees.add(new Employee("Franek", "Pietrowicz", 53, "48537605268", "franekpietrowicz72@gmail.com", "%Aarc&eo0", serviceList1));
        employees.add(new Employee("Katarzyna", "Pawlak", 27, "48666621994", "katarzynapawlak98@gmail.com", "lk#Dt3Y9sjY", serviceList2));
        employees.add(new Employee("Zofia", "Rutkowska", 33, "48781477302", "zofiarutkowska92@gmail.com", "87TI*&r2mO", serviceList2));
        employees.add(new Employee("Jacek", "Juda", 56, "48123456789", "jacekjuda123@gmail.com", "jacekjuda123@gmail.com", serviceList1));
        products.add(new Product("Szampon regenerujący", 45.99, 10, "Szampon"));
        products.add(new Product("Odżywka nawilżająca", 39.99, 15, "Odżywka"));
        products.add(new Product("Lakier do włosów", 29.99, 20, "Stylizacja"));
        products.add(new Product("Żel do włosów", 19.99, 25, "Stylizacja"));
        products.add(new Product("Maska keratynowa", 59.99, 8, "Maska"));


        reservations.add(new Reservation(client1, employees.get(0), service7, LocalDateTime.of(2026, Month.FEBRUARY, 1, 9, 0)));
        reservations.add(new Reservation(client2, employees.get(1), service2, LocalDateTime.of(2026, Month.FEBRUARY, 3, 10, 0)));
        reservations.add(new Reservation(client3, employees.get(2), service5, LocalDateTime.of(2026, Month.MARCH, 5, 12, 0)));
        reservations.add(new Reservation(client4, employees.get(1), service3, LocalDateTime.of(2026, Month.APRIL, 9, 13, 30)));
        reservations.add(new Reservation(client5, employees.get(0), service1, LocalDateTime.of(2026, Month.APRIL, 9, 16, 0)));
        reservations.add(new Reservation(client6, employees.get(2), service6, LocalDateTime.of(2026, Month.MAY, 9, 16, 30)));
        reservations.add(new Reservation(client7, employees.get(1), service9, LocalDateTime.of(2026, Month.MAY, 11, 17, 30)));
        reservations.add(new Reservation(client8, employees.get(3), service10, LocalDateTime.of(2026, Month.MAY, 11, 18, 0)));
        reservations.add(new Reservation(client9, employees.get(2), service8, LocalDateTime.of(2026, Month.MAY, 16, 19, 30)));
    }
}

