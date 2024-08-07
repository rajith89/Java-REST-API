TestBranch Web Application
==========================

Overview and Programming Technologies
-------------------------------------
	
	This document is a technical overview of the TestBranch web site.
	The application is mostly a Java REST API which serves the mobile client.
	It has a few JSP web pages which have mostly static content. It has a main home page and also terms and privacy pages.
	

Run, Build and Deploy 
---------------------
	
	This project is run as a Maven project.
	
	Go to Eclipse "Run configuration" and it will be available in the Maven Build.
	
	Local URL: http://localhost:8080/testbranch

	These values for Eclipse are added to the Maven run configuration: 
		Maven configuration goal for local properties: spring-boot:run -P dev
		Maven configuration for the production build: clean package -P prod
				
	A path needs to be added to the Run configuration environment to access Maven build
		Name: PATH
		Value: /Users/home/Documents/apache-maven-3.6.3/bin:${env_var:PATH}
		
				 	