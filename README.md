* Hiberate with postgresql
* We can add maven dependencies to add jars needed for it
* Hibernate is basically an ORM (Object relational mapping)
* In hibernate every row in a table corresponds to an object in java
* Every column refers to an attribute/variable in the class


* We normally add hibernate configuration in an xml file
* refer hibernate.cfg.xml
* refer Main.java to see how to connect
    1. Load Configuration
    2. Build session factory
    3. Open the session
    4. Begin transaction 
    5. Persist 
    6. Commit transcation 
* We can use @Entity annotation provide by Java persistence API
* We use JPA annotations
* @Id is needed on atleast one column to indicate a primary key
* CRUD - create -> persist method of session
         read -> find method of session
         update -> merge method of session (merge can also create if data is not present)
         delete -> remove method of session
* We can have different class name for a table and different property name for a column
* refer AlienEntity.java - we can use @Table and @Column annotations
* we can use @Transient annotation if we don't want the variable to be part of the table
* @Embeddable annotation can be used to embed fields from a different model into entity model
* We have different mapping annotations - @ManyToOne
                                          @OneToMany
                                          @ManyToMany
                                          @OneToOne
* OneToOne - AlienEntity has a OneToOne mapping with LaptopEntity
* OneToMany and ManyToOne - In this case we have to create a List of laptops
* Note that by just adding OneToMany/ManyToOne  
 it automatically creates a third mapping table, if we don't want to create 3rd table we   
 need to add a reference with mapped by and ManyToOne in the other table
* ManyToMany - Add @ManyToMany annotation in both the models and create lists
* This also creates 2 redundant mapping tables (alien_laptop and laptop_alien) due to
    ego clashes
* Use same Mapped by to fix this problem - ex: @ManyToMany(mappedBy = "laptopEntities")
* Lazy and Eager Fetching -
    Hibernate caches the data so in the same session if we insert and try to fetch
    it doesn't fire a select query again
    If we open a new session, it will fire a select query
    Refer Customer and Order and refer createCustomer in Main
    By Default it does lazy loading, if we want eager, we need to mention it in the annotation
    @OneToMany(fetch = FetchType.EAGER)
* Hibernate Caching - In the same session, if we fire a same query , it calls it only once
  and caches it. Sometimes it's bad because if data changed while we are firing the query, we may
    not get accurate result.
    In separate sessions, hibernate by default doesn't allow caching, we have to use some extensive
    caching mechanism
* HQL (Hibernate Query Language) - It is derived from SQL(we deal with tables), in HQL we deal with
    entities
* We can use session's createQuery method ex: session.createQuery("from SimpleLaptop where ram=32");
* query.getResultList(); this will give a list of filtered data based on the query
* We can also substitute a string value in the query using ? and index:
* Ex: String brand = "Dell";
      Query brand_based_query = session.createQuery("from SimpleLaptop where brand = ?1");
      brand_based_query.setParameter(1, brand);
* find vs byId,getReference (lazy vs eager loading) : refer getAndLoad method in main
* L1 cache is given by Hibernate as part of session
* 