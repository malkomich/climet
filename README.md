# Clime Tool for Java

[![Build Status](https://travis-ci.org/malkomich/climet.svg?branch=master)](https://travis-ci.org/malkomich/climet)

## Overview

This is a Java library wich provides a powerful tool for getting **weather information and forecasts** from any city you request.
The weather data comes from external APIs, like [Open Weather Map](http://http://openweathermap.org/).


## Install

**Gradle**

```
	repositories {
    	maven { url "https://jitpack.io" }
	}
```

```
	dependencies {
	    provided 'com.github.malkomich:climet:1.0'
	}
```

**Maven**

```
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
```

```
	<dependency>
	    <groupId>com.github.malkomich</groupId>
	    <artifactId>climet</artifactId>
	    <version>1.0</version>
	</dependency>
```

**SBT**

```
	resolvers += "jitpack" at "https://jitpack.io"
```

```
	libraryDependencies += "com.github.malkomich" % "climet" % "1.0"
```


## Usage

+ Getting current weather by a city name.

```
	CurrentWeatherData data = ClimeT.getCurrentWeather("London");
	City city = data.getCity();
	Weather weather = data.getWeather();

	// You have all weather information in the City and Weather objects.
	String state = weather.getState().getMain();
	float temp = weather.getTemp().getCurrentTemp(Weather.CELSIUS);

	System.out.println("The current weather in " + city.getName() + " is: " + state + ", with a temperature of "
			+ temp + "\u00b0.");
```
			
+ Getting hour forecast by coordinates.

```
	HourForecastData data = ClimeT.getHourForecast(51.51, -0.13);
	List<Weather> forecast = data.getForecast();

	Weather weather = forecast.get(2);
	Calendar date = weather.getDateTime();
	float speed = weather.getWind().getSpeed();
	int cloudiness = weather.getClouds().getCloudiness();

	System.out.println("The day " + sdf.format(date.getTime()) + " at " + date.get(Calendar.HOUR_OF_DAY)
			+ ", the wind will have a speed of " + speed + " m/s, and the percentage of cloudiness will be "
			+ cloudiness + "%.");
```
			
+ Getting date forecast by city.

```
	DateForecastData data = ClimeT.getDateForecast("London");
	List<Weather> forecast = data.getForecast();

	Weather weather1 = forecast.get(2);
	Calendar date1 = weather1.getDateTime();
	float rain = weather1.getRain().getValue();
	Weather weather2 = forecast.get(5);
	Calendar date2 = weather2.getDateTime();
	float snow = weather2.getSnow().getValue();

	String rainMessage = (rain > 0) ? (" there will be rainfall with a volume of " + rain + " l/m2")
			: " there won't be rainfall";
	String snowMessage = (snow > 0) ? (" there will be snowfall with a volume of " + snow + " l/m2.")
			: " there won't be snowfall.";

	System.out.println("In the day " + sdf.format(date1.getTime()) + rainMessage + ", and in the day "
			+ sdf.format(date2.getTime()) + snowMessage);
```
