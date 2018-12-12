FROM openjdk:8
ADD /taskmanager-api-0.0.1-SNAPSHOT.jar taskmanager-api.jar
EXPOSE 8089
RUN bash -c 'touch /taskmanager-api.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://localhost:27017/test","-jar","taskmanager-api.jar"]