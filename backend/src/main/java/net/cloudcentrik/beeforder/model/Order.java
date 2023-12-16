package net.cloudcentrik.beeforder.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "orders")
public class Order {
    @Id
    private String id;
    private String name;
    private String mobile;
    private float amount;
    private boolean done;
    private LocalDateTime orderedAt;

    public Order(String id, String name, String mobile, float amount, boolean done, LocalDateTime orderedAt) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.amount = amount;
        this.done = done;
        this.orderedAt = orderedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }
}
