Java REST API for CRUD 
==========================

Overview and Programming Technologies
-------------------------------------
	
	This document is a technical overview of the Java REST CRUD APIs.
	The application is mostly a Java REST API which serves the mobile/Web client.
	

Run, Build and Deploy 
---------------------
	
	This project is run as a Maven project.
	
	
	Local URL: http://localhost:8080/testbranch

	These values for Eclipse are added to the Maven run configuration: 
		Maven configuration goal for local properties: spring-boot:run -P dev
		Maven configuration for the production build: clean package -P prod
				
	A path needs to be added to the Run configuration environment to access Maven build
		Name: PATH
		Value: /Users/home/Documents/apache-maven-3.6.3/bin:${env_var:PATH}
		
Technolgoies Used
-----------------		

Java 1.8
Spring Boot 2.2.2
Mysql
flyway
swagger-ui
Spring Security with JWT
		
				 	
