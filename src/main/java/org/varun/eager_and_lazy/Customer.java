package org.varun.eager_and_lazy;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    private int cid;
    private String mobile;
    private String cname;
    //Not a good idea to eager load for large data set, use lazy
    //@OneToMany(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", mobile=" + mobile +
                ", cname='" + cname + '\'' +
                ", orders=" + orders +
                '}';
    }
}
