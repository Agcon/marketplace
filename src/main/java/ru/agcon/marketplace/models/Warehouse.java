package ru.agcon.marketplace.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty()
    @Column(name = "type_of_product")
    private String typeOfProduct;

    @NotEmpty
    @Column(name = "id_product")
    private int idProduct;

    @NotEmpty
    @Column(name = "amount")
    private int amount;

    public Warehouse() {
    }

    public Warehouse(String typeOfProduct, int idProduct, int amount) {
        this.typeOfProduct = typeOfProduct;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", typeOfProduct='" + typeOfProduct + '\'' +
                ", idProduct=" + idProduct +
                ", amount=" + amount +
                '}';
    }
}
