# Library  

## Description  

A jsf library to register books and authors.  

## Site  

[Application](http://libraryjsf.herokuapp.com/obra.xhtml)  

## Instructions 

* Before to use the system, you should click the button ``Gerar usuário`` in the login page.  
* Optionally you can also click click the button ``Popular banco`` to obtain a pre-registered set of data.  

The user's login that will be generated is:  

>  Email: teste@teste.com  
>  Senha: 123  

#### Running locally  

* Java: 1.8  
* Server: Wildfly 18.0.1  or Tomcat 8.5  
* Database: Postgres 9.6  

You should create the databases: libraryjsf  
> user: postgres  
> password: 123  
  
Config your project changing datasource through applicationContext.xml file:  

* Update ``entityManagerFactory`` bean's value from ``db_postgres_heroku`` to ``db_postgres``  

Save and add the project to the server.  
  
#### Wildfly Server  
- Copy the ``org`` folder inside ``resources/Config/WildflyDatabase`` from your project and paste inside ``modules`` folder localized in the root ``Wildfly folder``;  
  

- Copy the two codes bellow into the ``standalone-full.xml`` file in the Wildfly folder (``/standalone/configuration``):  

```
<datasource jndi-name="java:jboss/datasources/libraryDS" pool-name="libraryDS" enabled="true" use-java-context="true"> <!-- use-ccm="false" -->  
	<connection-url>jdbc:postgresql://localhost:5432/libraryjsf</connection-url>  
	<driver>postgresql</driver>  
	<pool>  
		<min-pool-size>10</min-pool-size>  
		<max-pool-size>20</max-pool-size>  
	</pool>  
	<security>  
		<user-name>postgres</user-name>  
		<password>123</password>  
	</security>  
	<validation>  
		<validate-on-match>false</validate-on-match>  
		<background-validation>false</background-validation>  
	</validation>  
	<statement>  
		<share-prepared-statements>false</share-prepared-statements>  
	</statement>  
</datasource>
```
```
<driver name="postgresql" module="org.postgresql">
	<driver-class>org.postgresql.Driver</driver-class>
	<!-- <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class> -->
</driver>  
```
    
#### Tomcat Server  
- Just add the project and play.

Now just start the server and ready to go! 