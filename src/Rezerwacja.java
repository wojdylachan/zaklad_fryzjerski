import java.time.LocalDate;

public class Rezerwacja {
    Klient klient;
    Usluga usluga;
    LocalDate dataWizywy;

    public Rezerwacja(Klient klient, Usluga usluga) {
        this.klient = klient;
        this.usluga = usluga;
//        this.dataWizywy = localDate;
    }

    public void showInfo() {
        System.out.println("Rezerwacja: ");
        klient.showInfo();
        usluga.showInfo();
    }
}
