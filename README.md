# Project Title

Forecast Weather

## Getting Started

Display the weather results in a grid and omit any locations that fail to return data. Store the
result data in a model, store it to Mongo as forecast of the day, and populate the record to UI using
thymeleaf. Log all parameters passed to server. Subsequent
call to display the weather results should check whether the today forecast record exists in database
before triggering another REST API call to #Darksky.

### Prerequisites

* JDK-8, JRE-8 or above
* Mongo DB

### Installing

* Import as maven project
* Import script to mongo DB:
	.\sql\parameter.json
* In application.properties, update:
	application.darksky.secret-key=76745b5a0f4e1bc18d1d4dbc10c28f44
## Running the tests

ForecastWeatherControllerTest: Perform hitting API to retrieve Weather data

## Built With

* [Maven](https://maven.apache.org/) - mvn clean install

## Contributing

* DarkSky

## Versioning

* 1.0

## Authors

* **Linh** - *Initial work* - Forecast Weather

## Demo
![alt text](https://i.gyazo.com/a294961e56e6e8bf65e122e8c160a6ad.jpg)

