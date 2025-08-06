package org.varun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        //Use session factory only once as it is heavyweight
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(org.varun.Student.class)
                .configure()
                .buildSessionFactory();
        Session session = sf.openSession();
//        new Main().createData(session);
        new Main().fetchData(session);
        sf.close();
    }

    public void createData(Session session) {
        //only needed when we are persisting
        Transaction tr = session.beginTransaction();
        Student s1 = new Student();

        s1.setsName("Anu");
        s1.setRollNo(107);
        s1.setsAge(65);
        //we had save before which is now deprecated in favour of persist
        session.persist(s1);
        tr.commit();
        session.close();

        System.out.println(s1);
    }

    public void fetchData(Session session) {
        Student s2 = null;
        //we had get before which is now deprecated in favour of find
        s2 = session.find(Student.class, 107);
        System.out.println(s2);
    }
}
