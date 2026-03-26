import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// ==========================
// 1. GEOCODING
// ==========================
def geoResponse = WS.sendRequest(findTestObject('Geocoding/geocoding_request'))
WS.verifyResponseStatusCode(geoResponse, 200)

def geoJson = new JsonSlurper().parseText(geoResponse.getResponseBodyContent())

def lat = geoJson[0].lat
def lon = geoJson[0].lon

println("LAT: " + lat)
println("LON: " + lon)

// ==========================
// 2. FORECAST
// ==========================
def forecastResponse = WS.sendRequest(findTestObject('Forecast/forecast_request', [('lat') : lat, ('lon') : lon]))

WS.verifyResponseStatusCode(forecastResponse, 200)

def forecastJson = new JsonSlurper().parseText(forecastResponse.getResponseBodyContent())

// BASIC ASSERTION
assert forecastJson.cod == "200"
assert forecastJson.list.size() > 0

// FIELD VALIDATION
assert forecastJson.list[0].main.temp != null
assert forecastJson.list[0].weather[0].main != null
assert forecastJson.list[0].dt_txt != null

// DATA VALIDATION
assert forecastJson.list[0].main.temp > 0
assert forecastJson.list[0].main.humidity >= 0 && forecastJson.list[0].main.humidity <= 100

// ==========================
// 3. AIR POLLUTION
// ==========================
def airResponse = WS.sendRequest(findTestObject('AirPollution/air_pollution_request', [('lat') : lat, ('lon') : lon]))

WS.verifyResponseStatusCode(airResponse, 200)

def airJson = new JsonSlurper().parseText(airResponse.getResponseBodyContent())

// BASIC ASSERTION
assert airJson.list.size() > 0

// FIELD VALIDATION
assert airJson.list[0].main.aqi != null
assert airJson.list[0].components.co != null
assert airJson.list[0].components.pm2_5 != null

// DATA VALIDATION
assert airJson.list[0].main.aqi >= 1 && airJson.list[0].main.aqi <= 5
assert airJson.list[0].components.co >= 0