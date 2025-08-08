package org.varun;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.varun.eager_and_lazy.Customer;
import org.varun.eager_and_lazy.Order;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Use session factory only once as it is heavyweight
//        SessionFactory sf_student = new Configuration()
//                .addAnnotatedClass(org.varun.Student.class)
//                .configure()
//                .buildSessionFactory();

//        SessionFactory sf_alien = new Configuration()
//                .addAnnotatedClass(org.varun.AlienEntity.class)
//                .addAnnotatedClass(org.varun.LaptopEntity.class)
//                .configure()
//                .buildSessionFactory();

//        SessionFactory sf_customer = new Configuration()
//                .addAnnotatedClass(org.varun.eager_and_lazy.Customer.class)
//                .addAnnotatedClass(org.varun.eager_and_lazy.Order.class)
//                .configure()
//                .buildSessionFactory();

        SessionFactory sf_laptop = new Configuration()
                .addAnnotatedClass(org.varun.SimpleLaptop.class)
                .configure()
                .buildSessionFactory();

        Session session = sf_laptop.openSession();
//        new Main().createData(session);
//        new Main().fetchData(session);
//        new Main().updateData(session);
//        new Main().deleteData(session);
//        new Main().createAlien(session);
//        new Main().fetchAlien(session);
        //For eager and lazy fetch refer below
//        new Main().createAndFetchCustomer(sf_customer);

        //data needed for HQL
//        new Main().hqlCreateLaptop(session);

        //fetching data differently
//        new Main().fetchUsingHql(session);
//        new Main().getAndLoad(session);
        new Main().caching(sf_laptop);
        sf_laptop.close();
    }

    public void caching(SessionFactory sf_laptop) {
        Session session = sf_laptop.openSession();

        //Query is executed only once even though we are fetching sl1 and sl2 due to L1 cache
        SimpleLaptop sl1 = session.find(SimpleLaptop.class, 2);
        System.out.println(sl1);

        SimpleLaptop sl2 = session.find(SimpleLaptop.class, 2);
        System.out.println(sl2);

        session.close();

        //New Query is fired due to different session.
        Session session1 = sf_laptop.openSession();
        SimpleLaptop sl3 = session1.find(SimpleLaptop.class, 2);
        System.out.println(sl3);
        session1.close();

    }

    public void getAndLoad(Session session) {
        //find is same as get, and when we use below, the query is fired to DB
        SimpleLaptop lp = session.find(SimpleLaptop.class, 2);

        //byId and getReference is same as load, different between this and find is
        //this does not fire a query unless we ask it to
        SimpleLaptop lp2 = session.byId(SimpleLaptop.class).getReference(1);

        //only now it fires a query
        System.out.println(lp2);
    }

    public void fetchUsingHql(Session session) {
        String brand = "Dell";

        Query ram_based_query = session.createQuery("from SimpleLaptop where ram=32");
        List<SimpleLaptop> ram_sls = ram_based_query.getResultList();

        Query brand_based_query = session.createQuery("from SimpleLaptop where brand = ?1");
        brand_based_query.setParameter(1, brand);
        List<SimpleLaptop> brand_sls = brand_based_query.getResultList();

        Query select_one_col = session.createQuery("select model from SimpleLaptop where brand = ?1");
        select_one_col.setParameter(1, brand);
        List<String> specific_one_col_sls = select_one_col.getResultList();

        Query select_mul_cols = session.createQuery("select brand, model from SimpleLaptop where brand = ?1");
        select_mul_cols.setParameter(1, brand);
        List<Object[]> select_mul_cols_sls = select_mul_cols.getResultList();

        System.out.println(ram_sls);
        System.out.println(brand_sls);
        System.out.println(specific_one_col_sls);

        for(Object[] data: select_mul_cols_sls) {
            System.out.println(data[0] + " " + data[1]);
        }
        session.close();

    }

    public void hqlCreateLaptop(Session session) {
        SimpleLaptop l = new SimpleLaptop();
        l.setSlid(4);
        l.setBrand("Apple");
        l.setModel("M1");
        l.setRam(32);
        Transaction t = session.beginTransaction();
        session.persist(l);
        t.commit();
        session.close();
    }

    public void createAndFetchCustomer(SessionFactory sf_customer) {

        Session session = sf_customer.openSession();
        Order o1 = new Order();
        o1.setOid(100);
        o1.setOrder_name("Package 1");

        Order o2 = new Order();
        o2.setOid(200);
        o2.setOrder_name("Package 2");

        Order o3 = new Order();
        o3.setOid(300);
        o3.setOrder_name("Package 3");

        Customer c1 = new Customer();
        c1.setCid(1);
        c1.setCname("Varun");
        c1.setMobile("8095120904");

        Customer c2 = new Customer();
        c2.setCid(2);
        c2.setCname("Chinmayi");
        c2.setMobile("8884212656");

        c1.setOrders(Arrays.asList(o1,o2));
        c2.setOrders(Arrays.asList(o3));

        Transaction tr = session.beginTransaction();
        session.persist(o1);
        session.persist(o2);
        session.persist(o3);
        session.persist(c1);
        session.persist(c2);
        tr.commit();

        Customer cn1 = session.find(Customer.class, 1);//does not fire a query as we are in the same session
        System.out.println(cn1);
        session.close();

        Session s1 = sf_customer.openSession();
        //By Default this will do a lazy fetch of Orders (beacuse of the one to many mapping)
        Customer cn2 = s1.find(Customer.class, 1);//this will fire a query as we are in a new session
        //when we request for it, it will fire a query to orders as well
        System.out.println(cn2);
        s1.close();
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

        AlienEntity a2 = new AlienEntity();
        a2.setAid(102);
        a2.setAlienName("Chinmayi");
        a2.setTech("Ruby");

        AlienEntity a3 = new AlienEntity();
        a3.setAid(103);
        a3.setAlienName("Parnika");
        a3.setTech("Python");

        //Laptop is embeddable into AlienEntity, columns are added to alien
        //a1.setLaptop(l1);

        //One to One
        //in below case alien has a one to one mapping to laptop
        //a1.setLaptopEntity(l1);

        //One to Many and Many to One
//        a1.setLaptopEntities(Arrays.asList(l1,l2,l3));
        //Do below if you don't want a third table to be created
//        l1.setAlienEntity(a1);
//        l2.setAlienEntity(a1);
//        l3.setAlienEntity(a1);

        //Many to Many
        a1.setLaptopEntities(Arrays.asList(l1,l2));
        a2.setLaptopEntities(Arrays.asList(l2,l3));
        a3.setLaptopEntities(Arrays.asList(l1));

        l1.setAlienEntities(Arrays.asList(a1, a3));
        l2.setAlienEntities(Arrays.asList(a1, a2));
        l3.setAlienEntities(Arrays.asList(a1));

        Transaction tr = session.beginTransaction();
        session.persist(l1);
        session.persist(l2);
        session.persist(l3);
        session.persist(a1);
        session.persist(a2);
        session.persist(a3);
        tr.commit();
        session.close();
    }

    public void fetchAlien(Session session) {
        AlienEntity a = null;

        a = session.find(AlienEntity.class, 102);
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
