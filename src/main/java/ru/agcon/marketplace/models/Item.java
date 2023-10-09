package ru.agcon.marketplace.models;

import java.util.Objects;

public class Item {
    private int idProduct;
    private String typeOfProduct;

    public Item(int idProduct, String typeOfProduct) {
        this.idProduct = idProduct;
        this.typeOfProduct = typeOfProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idProduct=" + idProduct +
                ", typeOfProduct='" + typeOfProduct + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return idProduct == item.idProduct && typeOfProduct.equals(item.typeOfProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, typeOfProduct);
    }
}
