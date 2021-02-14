package Projekt;

public class Car implements Comparable<Car>{
    int price;
    String color;
    String brand;
    String model;
    String[] inventory;

    public Car(String brand, String model, String color, int price) {
        this.price = price;
        this.color = color;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public int compareTo(Car o) {
        if (this.price != o.price) {
            return this.price - o.price;
        }
        return this.brand.compareTo(o.brand);
    }
}