******** Create a Jakarta EE envirement *******

====>project structure:

1- the first step is to create a maven project using idle like intellij
2- go to your pom.xml file and choose the type of packaging you want to create (in our case we gonna choose war for what is provide as it the standard approche when creating a jakarta EE , java EE  project  ) 
3- install tomCat lastversion (in my case 10) then connect it with my project using "war"

===
==> dependencies:
("
==Explanation of the elements:

<groupId>: Identifies the group or organization that created the library. In this case, it's "jakarta.persistence."

<artifactId>: Specifies the name of the library or artifact. Here, it's "jakarta.persistence-api."

<version>: Specifies the version of the library you want to use. In this case, it's "3.0.0."

<scope>: Defines the scope of the dependency. "Provided" means that the dependency is expected to be provided by the runtime environment and should not be included in the final package (JAR or WAR).
")


1- Hibernate.core dependency:
`` <dependency>
      <groupId>org.hibernate.orm</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>6.0.2.Final</version>
    </dependency>
``

Hibernate is an Object-Relational Mapping (ORM) framework that simplifies database interaction in Java applications.

2-Servlet API:

``
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>5.0.0</version>
            <scope>provided</scope>
        </dependency>
``
This dependency provides the Jakarta Servlet API, which is necessary for developing servlets in your application. The scope "provided" indicates that the dependency is expected to be provided by the runtime environment.

3-JDBC Driver:
you can add any jdbc driver depending on what sgbd your gonna use for your project

``
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <version>42.6.0</version>
    </dependency>
``

This is the JDBC driver for PostgreSQL, enabling your Java application to connect to a PostgreSQL database.

4-Persistence JPA:

``
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.0.0</version>
        <scope>provided</scope>
    </dependency>
``
The dependency you provided is for the Jakarta Persistence API, which is used in Java EE or Jakarta EE applications for working with persistence and databases. 


=== 
==>persistence file:

- now after adding dependencies that you need  , you can create your entities , then you need the configurate your persistence.xml file (if you didn't generate it when creating your project , you can just create one) :

``
    <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
                version="3.0">
    <persistence-unit name="my-persistence-unit" transaction-type="RESOURCE_LOCAL">
        <properties>
        <property name="jakarta.persistence.jdbc.driver" value="--add databese driver path-"/>
        <property name="jakarta.persistence.jdbc.url" value="-- add the database URL --"/>
        <property name="jakarta.persistence.jdbc.user" value="--your database username--"/>
        <property name="hibernate.show_sql" value="true"/>
        <property name="jakarta.persistence.jdbc.password" value="--your database password--"/>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
    </persistence>

``
1-jakarta.persistence.jdbc.driver:Specifies the JDBC driver class for your database.
                                in my case sence i'm using potgres Driver my value attribute while be like the following:
                                " Value: com.postgres.cj.jdbc.Driver"

2-jakarta.persistence.jdbc.url:Specifies the JDBC URL for your database.
                               this is an example what the Database Url should be like:
                               "Value: jdbc:postgresql://localhost:5432/Event"

3-jakarta.persistence.jdbc.user:Specifies the username for connecting to the database.
                                example : This is the username for connecting to the PostgreSQL database.
                                Value: postgres

4-jakarta.persistence.jdbc.password:Specifies the password for connecting to the database.
                                    example:This is the password for connecting to the PostgreSQL database.
                                    Value: Password

5-hibernate.show_sql:Specifies whether to log SQL statements to the console.
                     Value: true
                    When set to true, Hibernate will print SQL statements to the console.

6-hibernate.hbm2ddl.auto:Specifies the Hibernate feature for automatic DDL generation.
                         Value: update
                         This setting allows Hibernate to automatically update the database schema based on changes in the entity classes.
                         depending on the value you can controle DDL generation :
                            --create:
                                    Hibernate will attempt to create the database schema from scratch.
                                    It will drop existing tables and recreate them, potentially resulting in data loss.
                            --update:
                                    Hibernate will update the database schema to reflect the current configuration.
                                    It will add new tables, columns, or constraints, but it won't drop or modify existing ones.
                            --validate:
                                    Hibernate will validate the database schema against the current configuration.
                                    It won't make any changes to the database.
                            --create-drop:
                                    Similar to create, but the database schema will be dropped when the SessionFactory is closed.
                                    This is useful for testing and development, but it can lead to data loss.
                            --none:
                                    Hibernate will not perform any automatic schema generation or validation.
                                    You are responsible for ensuring that the database schema matches the Hibernate configuration.

===
==> servlet configuration :

This is the last step in our configuration , in wiche we gonna automatic DDL generation , when running the project with what we've done until now , my entities can be created in my database , and the simple raison why , creation of an EntityManagerFactory==>(It manages the database connection(s) associated with the persistence unit. When an EntityManagerFactory is created, it establishes a connection pool, and each EntityManager obtained from the factory operates within the context of this connection pool.), and that's exacly what we gonna implement in our code:
-first step is the create an EntityManagerFactory:
 ``
    private final EntityManagerFactory entityManagerFactory;

    public Repository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

 ``
 ``
     private final Repository repository;

    public Service(){
        repository=new Repository();
    }

 ``
-next is to create a new servlet where we gonna call our service in the init methode :

``

        @WebServlet(value = "",loadOnStartup = 1)
        public class DefaultServlet extends HttpServlet {
            @Override
            public void init(){
                Service Service=new Service();
            }
        }

``
-- The loadOnStartup attribute in the @WebServlet annotation is used to specify the order in which servlets should be initialized when the web application starts. It is an optional attribute that takes an integer value, and servlets with lower values for loadOnStartup will be initialized before servlets with higher values.
-- in the example above, the loadOnStartup attribute is set to 1. This means that the servlet with this annotation will be initialized during the startup of the web application, and its init method will be called before any servlets with a higher loadOnStartup value.

=====>with this we can say that our project configuration is done
