To build project 



eureka server
```
cd eureka
gradlew build
```


config server (importent)
```
cd config-service
gradlew build
```


zuul
```
cd zuul
gradlew build
```


main applaction
```
cd vacation-service
gradlew build
```


service UI
```
cd vacayionInterface
npm install
npm run-script build --prod
```
```
cd compose
docker-compose build
docker-compose up

and then wait for a few minutes check if http://localhost:8761/ make sure 3 services up and running.

Front end url: http://localhost:4200/VacationRequest


