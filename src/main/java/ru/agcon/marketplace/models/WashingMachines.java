package ru.agcon.marketplace.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "washingmachines")
public class WashingMachines {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "producer")
    private String producer;

    @Column(name = "tank_capacity")
    private int tankCapacity;

    @Column(name = "number_of_seller")
    private int numberOfSeller;

    @Column(name = "type_of_product")
    private String typeOfProduct;

    @Column(name = "price")
    private int price;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name")
    private String name;

    // Конструктор по умолчанию нужен для Spring
    public WashingMachines() {
    }

    public WashingMachines(String producer, int tankCapacity, int numberOfSeller, int price, String name) {
        this.producer = producer;
        this.tankCapacity = tankCapacity;
        this.numberOfSeller = numberOfSeller;
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

    public int getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public int getNumberOfSeller() {
        return numberOfSeller;
    }

    public void setNumberOfSeller(int numberOfSeller) {
        this.numberOfSeller = numberOfSeller;
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
        return "WashingMachines{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", tank_capacity=" + tankCapacity +
                ", numberOfSeller=" + numberOfSeller +
                ", typeOfProduct='" + typeOfProduct + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
