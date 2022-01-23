### n11 TalentHub Bootcamp Graduation Project

#### App initialize

* Open a terminal and go to project`s location;

 **for Windows:** 
```sh
cd {project-location}\n11-talenthub-bootcamp-graduation-project-bayraktar07
```

 **for Linux:**  
```sh
cd {project-location}/n11-talenthub-bootcamp-graduation-project-bayraktar07
```

> *Example Location:* C:\Users\TUGRUL\IdeaProjects\n11-talenthub-bootcamp-graduation-project-bayraktar07

* To package the project; 

```sh
mvn package -DskipTests=true
```

> I skipped tests because its database setup is for docker container and tests fails when it tries to connect to the database.

* To run the project on Docker;

```sh
docker-compose up
```

* Access to Swagger UI from http://localhost:8080/swagger-ui.html

* Documentation is [here].

#### Project Requirements

* [Maven] - Software project management and comprehension tool
* [Docker] - Open source containerization platform

[Docker]: <https://www.docker.com/products/docker-desktop>
[Maven]: <https://maven.apache.org>
[here]: <https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-bayraktar07/blob/main/src/main/resources/documentation/openapi.yaml>