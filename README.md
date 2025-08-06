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
