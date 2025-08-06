package org.varun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student();

        s1.setsName("Rama");
        s1.setRollNo(105);
        s1.setsAge(84);

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(org.varun.Student.class);
        cfg.configure();
        //Use session factory only once as it is heavyweight
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.persist(s1);
        tr.commit();

        System.out.println(s1);

    }
}
