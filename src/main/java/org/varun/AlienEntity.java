package org.varun;

import jakarta.persistence.*;

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

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + alienName + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }
}
