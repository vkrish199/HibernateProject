package org.varun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        //Use session factory only once as it is heavyweight
        SessionFactory sf_student = new Configuration()
                .addAnnotatedClass(org.varun.Student.class)
                .configure()
                .buildSessionFactory();

//        SessionFactory sf_alien = new Configuration()
//                .addAnnotatedClass(org.varun.AlienEntity.class)
//                .configure()
//                .buildSessionFactory();

        Session session = sf_student.openSession();
//        new Main().createData(session);
        new Main().fetchData(session);
//        new Main().updateData(session);
//        new Main().deleteData(session);
//        new Main().createAlien(session);
//        new Main().fetchAlien(session);
        sf_student.close();
    }

    public void createAlien(Session session) {
        AlienEntity a1 = new AlienEntity();
        a1.setAid(101);
        a1.setAlienName("Varun");
        a1.setTech("Java");
        Transaction tr = session.beginTransaction();
        session.persist(a1);
        tr.commit();
        session.close();
    }

    public void fetchAlien(Session session) {
        AlienEntity a = null;

        a = session.find(AlienEntity.class, 101);
        System.out.println(a);
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
