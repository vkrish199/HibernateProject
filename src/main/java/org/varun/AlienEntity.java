package org.varun;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="alien")
public class AlienEntity {

    @Id
    private int aid;
    @Column(name="aname")
    private String alienName;
    private String tech;
    @Transient
    private int counter;
//    private LaptopEmbeddable laptop;
//    @OneToOne
//    private LaptopEntity laptopEntity;
    //OneToMany automatically creates a third table alien_laptop
    //if we don't want a third table, we can create a reference in laptop table
    @OneToMany(mappedBy = "alienEntity")
    private List<LaptopEntity> laptopEntities;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAlienName() {
        return alienName;
    }

    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public List<LaptopEntity> getLaptopEntities() {
        return laptopEntities;
    }

    public void setLaptopEntities(List<LaptopEntity> laptopEntities) {
        this.laptopEntities = laptopEntities;
    }

    //    public LaptopEmbeddable getLaptop() {
//        return laptop;
//    }
//
//    public void setLaptop(LaptopEmbeddable laptop) {
//        this.laptop = laptop;
//    }

    @Override
    public String toString() {
        return "AlienEntity{" +
                "aid=" + aid +
                ", alienName='" + alienName + '\'' +
                ", tech='" + tech + '\'' +
                ", counter=" + counter +
                ", laptopEntities=" + laptopEntities +
                '}';
    }
}
