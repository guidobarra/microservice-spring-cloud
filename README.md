# microservice-spring-cloud
create microservices, api gateway and eureka discovery/service

## run app
docker-compose -f ./docker/docker-compose.yml --project-directory ./docker up


## app microservice-user startup and load balancer

change parameter Deureka.instance.instance-id
```
java -jar -Dspring.profiles.active=dev -Deureka.instance.instance-id=micro-user-01 ./microservice-user/target/microservice-user-1.0-SNAPSHOT.jar
```

