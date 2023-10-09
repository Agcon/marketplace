package ru.agcon.marketplace.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "author")
    private String author;

    @Column(name = "number_of_seller")
    private int numberOfSeller;

    @Column(name = "type_of_product")
    private String typeOfProduct;

    @Column(name = "price")
    private int price;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name")
    private String name;

    public Books() {
    }

    public Books(String author, int numberOfSeller, int price, String name) {
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", numberOfSeller=" + numberOfSeller +
                ", typeOfProduct='" + typeOfProduct + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
