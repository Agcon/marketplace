package ru.agcon.marketplace.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "phones")
public class Phones {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "producer")
    private String producer;

    @Column(name = "number_of_seller")
    private int numberOfSeller;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Column(name = "type_of_product")
    private String typeOfProduct;

    @Column(name = "price")
    private int price;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name")
    private String name;

    // Конструктор по умолчанию нужен для Spring
    public Phones() {
    }

    public Phones(String producer, int numberOfSeller, int batteryCapacity, int price, String name) {
        this.producer = producer;
        this.numberOfSeller = numberOfSeller;
        this.batteryCapacity = batteryCapacity;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getNumberOfSeller() {
        return numberOfSeller;
    }

    public void setNumberOfSeller(int numberOfSeller) {
        this.numberOfSeller = numberOfSeller;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", numberOfSeller=" + numberOfSeller +
                ", batteryCapacity=" + batteryCapacity +
                ", typeOfProduct='" + typeOfProduct + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
