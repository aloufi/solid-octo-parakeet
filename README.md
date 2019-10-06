To build project 



cd eureka
gradlew build


```
cd config-service
gradlew build
```

```
cd zuul
gradlew build
```

```
cd vacation-service
gradlew build
```


```
cd vacayionInterface
npm install
npm run-script build --prod
```
```
cd compose
docker-compose build
docker-compose up

and then wait for a few minutes check if http://localhost:8761/this work.

Front end url: http://localhost:4200/VacationRequest


