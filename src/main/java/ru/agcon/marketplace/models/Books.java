package ru.agcon.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
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

    public Books(String author, int numberOfSeller, int price, String name) {
        this.author = author;
        this.numberOfSeller = numberOfSeller;
        this.price = price;
        this.name = name;
    }

}
