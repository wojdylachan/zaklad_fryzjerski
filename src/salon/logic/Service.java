package salon.logic;

import salon.enums.ServiceType;

public class Service {
    protected String name;
    protected double price;
    protected int duration;
    protected ServiceType serviceType;
    protected String description;

    public Service(String name, double price, int duration, ServiceType serviceType, String description) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.serviceType = serviceType;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void showInfo() {
        System.out.println(name + " | " + (int) price + " zl | " + (int) duration + " min | " + description);
    }
}
