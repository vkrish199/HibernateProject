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