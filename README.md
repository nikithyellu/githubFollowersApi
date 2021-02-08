# githubFollowersApi
Api to fetch followers based on the github id. It returns upto 5 followers and data upto 3 levels deep

Table of Contents

What you'll build What you'll need Build and run the Spring Boot web app locally

This article walks you through setting up the app locally and testing the API

What you'll build

You'll clone the spring boot app from GitHub and then run it locally
What you'll need

The following prerequisites are required in order to follow steps in this article:

	•	An up-to-date Java Development Kit (JDK), version 1.8 or later.

	•	Git client

	•	Text editor
Build and run the Spring Boot web app locally

	•	Open a terminal window.

	•	Create a local directory to hold your Spring Boot application by typing mkdir SpringBoot

	•	Change to that directory by typing cd SpringBoot.

	•	Clone the Spring Boot sample project into the directory you created by typing git clone 

	•	Change to the directory of the project /complete

	•	Open complete/src/main/resources/application.yml in a text editor and replace *'s with your github token. 
	eg: authorization: token 123456abc789545125def

	•	Token mentioned in the above step can be created in Github by following below link
	https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token

	•	Build the JAR file using Maven by typing ./mvnw clean package

	•	When the web app has been created, start it by typing ./mvnw spring-boot:run

	•	Test it locally by visiting http://localhost:9090/followers?id="pass gitHub id of the user"

	•	You should see the response in a Json format
