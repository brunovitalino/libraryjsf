# Library

## Description

A jsf library to register books and authors.

## Site

[Application](https://libraryjsf.herokuapp.com/index.html)

## Instructions to run locally

* Java: 1.8  
* Server: Wildfly 18.0.1  
* Database: Postgres 9.6  

You should create the databases: libraryjsf  
> user: postgres  
> password: 123  
  
Config your project and add it to server;  
  
``Wildfly Server``: copy the "org" folder inside "resources/Config/WildflyDatabase" from your project and paste inside "modules" folder localized in the root Wildfly folder.  
  
Now just start the server and ready to go! 