package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }


    public void setOrders(Orders orders) {
        this.orders = orders;
        settingTotalPrice();
    }

    public void settingTotalPrice(double priceFound){
        double priceToAdd = this.orders.getTotalCost() + priceFound;
        this.orders.setTotalCost(priceToAdd);
    }

    public void settingTotalPrice() {
        double priceToAdd = this.orders.getTotalCost() + this.price;
        this.orders.setTotalCost(priceToAdd);
    }

    public void reducingTotalPrice(){
        double priceToAdd = this.orders.getTotalCost() - this.price;
        this.orders.setTotalCost(priceToAdd);
    }


}