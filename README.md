Capsule Task Manager Case Study Build Notes:

Application Structure: Parent maven project(taskmanager-aggregator) with two dependent modules - For building nd packaging both service and ui into a jar

taskmanager-api - Maven Spring boot project for exposing rest endpoints + Mongo DB
task-manager-ui - Angular CLI for building UI and connects with rest endpoints exposed
Git Repository: https://github.com/thangavelindian1/taskmanager-aggregator.git

Maven Build Commands for the final artifacts:

mvn clean install -e	[run the command for taskmanager-aggregator project which will build UI and service project and create the final jar with required resources]
mvn package docker:build	[run the command for taskmanager-api project which will copy the jar from target and create image in the remote docker container]
Commands to run the created image and validate the image:

Connect to the remote docker machine
To check whether the image is created in docker container dockerx image ls
To run the created image in docker dockerx run -p 8086:8086 tl-task-manager:latest
To check whether the image is running in docker[open new cmd prompt and run the cmd] dockerx ps
To validate whether the application is working fine using curl command i) connect to bash shell in the container. [take container id of the image created from dockers ps] dockerx exec -it [CONTAINER_ID] bash ii) check whether application is working [it will return custom techincal error from the service exposed since mongo db i not available in docker] curl -H "Accept:/" -H "Content-Type:application/json" -X http://localhost:8085/task-manager/viewtasks
Commands for local development:

Make sure mongodb is running in localhost with port 27017 [mongodb://localhost:27017/test]
Run maven command spring-boot:run for the project taskmanager-api
Check whether the endpoint is working fine in postman Endpoint : http://localhost:8085/task-manager/viewtasks Request: {} Method: GET Headers: Accept:/ Content-Type:application/json Response: [ { "_id": { "timestamp": 1540283478, "machineIdentifier": 15246859, "processIdentifier": 3124, "counter": 1753997, "time": 1540283478000, "date": 1540283478000, "timeSecond": 1540283478 }, "taskId": 1, "taskName": "task 1", "parentTaskDetail": null, "priority": "10", "startDate": "12/11/2018", "endDate": "12/19/2018" }, { "_id": { "timestamp": 1540283553, "machineIdentifier": 15246859, "processIdentifier": 3124, "counter": 1753998, "time": 1540283553000, "date": 1540283553000, "timeSecond": 1540283553 }, "taskId": 2, "taskName": "task 2", "parentTaskDetail": { "parentId": 1, "parentTaskName": "task 1" }, "priority": "28", "startDate": "12/11/2018", "endDate": "12/19/2018" } ]
Open the folder src/main/web of project task-manager-ui in visual studio and then run below commands npm install npm start
Hit the url http://loalhost:4200 and see whether the page is getting loaded
Jenkins command:

Make sure Jenkins installed and running
Configure Maven and JDK in jenkins with name maven3 and jdk1.8
Create Jenkins project with Pipeline option and configure the below information i) Github repository with credentials ii) Branch to build: */develop iii) Path to Jenkinsfile ->
Note:

All the configurations are available in pom.xml of respective projects
