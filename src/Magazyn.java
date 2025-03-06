//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//class Magazyn {
//    private List<Product> produkty = new ArrayList<>();
//
//    public void dodajProdukt(Product produkt) {
//        produkty.add(produkt);
//        System.out.println("Dodano: " + produkt.nazwa);
//    }
//
//    public void usunProdukt(String nazwa) {
//        produkty.removeIf(p -> p.nazwa.equalsIgnoreCase(nazwa));
//        System.out.println("Usunięto produkt: " + nazwa);
//    }
//
//    public void aktualizujIlosc(String nazwa, int nowaIlosc) {
//        for (Product p : produkty) {
//            if (p.nazwa.equalsIgnoreCase(nazwa)) {
//                p.ilosc = nowaIlosc;
//                System.out.println("Zaktualizowano ilość: " + p);
//                return;
//            }
//        }
//        System.out.println("Produkt nie znaleziony: " + nazwa);
//    }
//    public void wyswietlProdukty() {
//        System.out.println("::: Lista Produktow :::");
//        for (Product p : produkty) {
//            System.out.println(p);
//        }
//    }
//    public Product findProduct(){
//        System.out.println("Wpisz szukany produkt:");
//        String wybor6 = scan.nextLine().toLowerCase().trim();
//        for(Product p : magazyn.produkty)
//    }
//
//}
