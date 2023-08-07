**REST weather logging service**

**Terms of Reference for REST API service** : 

Create a REST API service that will accept data from a weather sensor.
The sensor measures the air temperature and determines if it is raining or not.
Every time it will make a measurement, it will send an HTTP request with data in JSON format to our server

**The REST API is designed and developed on the stack:**

 * JPA. Hibernate (Postgres)
 * Spring Boot
 * Spring validator
 * Spring MVC
 * Maven

**The API implements the following features**:

1. WeatherApp :
* Sensor registration("/registration")
* Adding a measurement from a sensor("/measurements/add")
* Getting all measurements("/measurements")
* Getting the number of rainy days("/measurements/rainyDaysCount")
2. WeatherClient
* Register a new sensor
* Sends requests with random data to the address("/measurements/add")
