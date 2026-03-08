package salon.logic;

import salon.model.Client;
import salon.model.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    protected Client client;
    protected Employee employee;
    protected Service service;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Client getClient() {
        return client;
    }

    public Service getService() {
        return service;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Reservation(Client client, Employee employee, Service service, LocalDateTime startTime) {
        this.client = client;
        this.employee = employee;
        this.service = service;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(service.getDuration());
    }

    public void showInfo() {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("[" + startTime.format(dtf1) + "] [" + startTime.format(dtf2) + " - " + endTime.format(dtf2) + "] " + client.showName() + " - " + employee.getFirstName() + service.name + " | " + (int) service.price + " zl | " + (int) service.duration + " min");
    }
}
