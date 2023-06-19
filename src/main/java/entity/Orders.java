package entity;

import entity.enums.PaymentType;
import entity.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "total_cost")
    private double totalCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private StatusOrder statusOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Product> products;

    public Orders(LocalDate orderDate, String deliveryAddress, StatusOrder statusOrder, PaymentType paymentType) {
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.totalCost = 0.0;
        this.statusOrder = statusOrder;
        this.paymentType = paymentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", totalCost=" + totalCost +
                ", statusOrder=" + statusOrder +
                ", paymentType=" + paymentType +
                '}';
    }

}