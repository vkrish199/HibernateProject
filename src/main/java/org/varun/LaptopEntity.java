package org.varun;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="laptop")
public class LaptopEntity {

    @Id
    private int lid;
    private String brand;
    private String model;
    private int ram;
//    @ManyToOne
//    private AlienEntity alienEntity;
    @ManyToMany(mappedBy = "laptopEntities")
    private List<AlienEntity> alienEntities;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public List<AlienEntity> getAlienEntities() {
        return alienEntities;
    }

    public void setAlienEntities(List<AlienEntity> alienEntities) {
        this.alienEntities = alienEntities;
    }

    @Override
    public String toString() {
        return "LaptopEntity{" +
                "lid=" + lid +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                '}';
    }
}
