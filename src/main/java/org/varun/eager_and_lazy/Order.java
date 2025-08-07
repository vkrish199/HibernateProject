package org.varun.eager_and_lazy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private int oid;
    private String order_name;
    @Transient
    private int counter;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", order_name=" + order_name +
                ", counter=" + counter +
                '}';
    }
}
