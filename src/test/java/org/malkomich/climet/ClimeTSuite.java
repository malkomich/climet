package org.malkomich.climet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCurrentWeather.class, TestDateForecast.class, TestDateWeather.class, TestHourForecast.class,
		TestWeather.class })
public class ClimeTSuite {

}