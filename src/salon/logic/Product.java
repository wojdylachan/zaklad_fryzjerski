package salon.logic;

public class Product {
    private String name;
    private double price;
    private int amount;
    private String category;

    public Product(String name, double price, int amount, String category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void showInfo() {
        System.out.println(name + " | " + category + " | " + price + " PLN | Ilość: " + amount);
    }
}

