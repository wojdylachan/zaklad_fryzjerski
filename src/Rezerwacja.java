import java.time.LocalDate;
import java.time.LocalTime;

public class Rezerwacja {
    Klient klient;
    Usluga usluga;
    LocalTime startTime;
    LocalTime endTime;

    public Rezerwacja(Klient klient, Usluga usluga, LocalTime startTime) {
        this.klient = klient;
        this.usluga = usluga;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(usluga.czasTrwania);
    }

    public void showInfo() {
        System.out.println("[" + startTime.toString() + " - " + endTime.toString() + "] " + klient.showName() + " - " + usluga.nazwa + " | " + (int) usluga.cena + " zl | " + (int) usluga.czasTrwania + " min");
    }
}
