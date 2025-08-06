package org.varun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Use session factory only once as it is heavyweight
//        SessionFactory sf_student = new Configuration()
//                .addAnnotatedClass(org.varun.Student.class)
//                .configure()
//                .buildSessionFactory();

        SessionFactory sf_alien = new Configuration()
                .addAnnotatedClass(org.varun.AlienEntity.class)
                .addAnnotatedClass(org.varun.LaptopEntity.class)
                .configure()
                .buildSessionFactory();

        Session session = sf_alien.openSession();
//        new Main().createData(session);
//        new Main().fetchData(session);
//        new Main().updateData(session);
//        new Main().deleteData(session);
        new Main().createAlien(session);
//        new Main().fetchAlien(session);
        sf_alien.close();
    }

    public void createAlien(Session session) {
//        LaptopEmbeddable l1 = new LaptopEmbeddable();
//        l1.setBrand("Asus");
//        l1.setModel("Rog");

        LaptopEntity l1 = new LaptopEntity();
        l1.setLid(1);
        l1.setBrand("Asus");
        l1.setModel("Rog");
        l1.setRam(16);

        LaptopEntity l2 = new LaptopEntity();
        l2.setLid(2);
        l2.setBrand("MSI");
        l2.setModel("Titan");
        l2.setRam(32);

        LaptopEntity l3 = new LaptopEntity();
        l3.setLid(3);
        l3.setBrand("Dell");
        l3.setModel("XPS");
        l3.setRam(24);

        AlienEntity a1 = new AlienEntity();
        a1.setAid(101);
        a1.setAlienName("Varun");
        a1.setTech("Java");

        //Laptop is embeddable into AlienEntity, columns are added to alien
        //a1.setLaptop(l1);
        //in below case alien has a one to one mapping to laptop
        //a1.setLaptopEntity(l1);
        a1.setLaptopEntities(Arrays.asList(l1,l2,l3));
        //Do below if you don't want a third table to be created
        l1.setAlienEntity(a1);
        l2.setAlienEntity(a1);
        l3.setAlienEntity(a1);

        Transaction tr = session.beginTransaction();
        session.persist(l1);
        session.persist(l2);
        session.persist(l3);
        session.persist(a1);
        tr.commit();
        session.close();
    }

    public void fetchAlien(Session session) {
        AlienEntity a = null;

        a = session.find(AlienEntity.class, 101);
        System.out.println(a);
        session.close();
    }

    public void createData(Session session) {
        //only needed when we are persisting
        Transaction tr = session.beginTransaction();
        Student s = new Student();

        s.setsName("Anu");
        s.setRollNo(107);
        s.setsAge(65);
        //we had save before which is now deprecated in favour of persist
        session.persist(s);
        tr.commit();
        session.close();

        System.out.println(s);
    }

    public void fetchData(Session session) {
        Student s = null;
        //we had get before which is now deprecated in favour of find
        s = session.find(Student.class, 107);
        session.close();
        System.out.println(s);
    }

    public void updateData(Session session) {
        Student s = new Student();

        s.setsAge(2);
        s.setRollNo(104);
        s.setsName("Parnika");
        //since it's an update, we need transaction
        Transaction tr = session.beginTransaction();
        //merge will fire a select query and will either insert/update based on data
        session.merge(s);
        tr.commit();
        session.close();
        System.out.println(s);
    }

    public void deleteData(Session session) {
        Student s = null;
        s = session.find(Student.class, 107);
        Transaction tr = session.beginTransaction();
        session.remove(s);
        tr.commit();
        session.close();
    }
}
