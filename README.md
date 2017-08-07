# FlightsList

## Description

* **Brief**

	This server has the functionality of delivere all data about Flights of one company.
	
* **Archtecture**

	Backend server builded on Java 8, using the patterns DDD, VO, DTO, JPA and Aspect, based on SpringBoot Framework.


## Build

* **Build database server using Docker**

	- First do a "Pull" of image "Postgres" using docker prompt.
  
		`docker pull postgres`
	
	- Next step, build a container, setting the basic informations.
  
		`docker run -p 5432:5342 --name flightlistdb -e POSTGRES_PASSWORD=postgres -e POSTGRES_USERNAME=postgres -e POSTGRES_DB=flightslist -d postgres`
		
	- Get the IP of docker-machine, using below command.
	
		`docker-machine ip`

* **Build project using Maven**

	- First do the "Clone" of repository on your workspace.
	
		`git clone https://github.com/dvdpansardis/flightslist.git`
		
	- Next step do the "Deploy" of system, to create the database model and import initial data to test, run the command below 
	on the terminal of gitbash on the your workspace and atention on place "USE_IP_OF_DOCKER_MACHINE" use the ip of docker machine getted on previous step.
	
		`mvn clean install -Dspring.profiles.active=deploy -Dsettings.ip.database=USE_IP_OF_DOCKER_MACHINE`
	
	- To run the server, you can use the "Dev" profile, using with the comand below.	
  
		`mvn spring-boot:run -Dspring.profiles.active=dev -Dsettings.ip.database=USE_IP_OF_DOCKER_MACHINE`
		
## Future Improvements

* **Improvements**

	- Create the Class "Passangers".
	- Create the Class "Airports".
	- Create the Test on the Controllers.
	- Create the Prodution profile.