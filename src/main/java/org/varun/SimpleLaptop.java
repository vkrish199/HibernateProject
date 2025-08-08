package org.varun;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simple_laptop")
public class SimpleLaptop {

    @Id
    private int slid;
    private int ram;
    private String model;
    private String brand;

    public int getSlid() {
        return slid;
    }

    public void setSlid(int slid) {
        this.slid = slid;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "SimpleLaptop{" +
                "slid=" + slid +
                ", ram=" + ram +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
