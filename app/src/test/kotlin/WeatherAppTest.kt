package hu.vanio.kotlin.feladat.ms

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import org.springframework.util.Assert
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.fail

class WeatherAppTest {
    @Test
    fun getDailyAverageOK() {
        val df = SimpleDateFormat("yyyy-MM-dd")
        var testString = "test"
        var testDate = df.parse("2024-03-20")

        val testWeatherClient = Mockito.mock(HttpClient::class.java)
        Mockito.`when`(testWeatherClient.get(testString)).thenReturn("{\"latitude\":47.5,\"longitude\":19.0625,\"generationtime_ms\":5.026941299438476562,\"utc_offset_seconds\":3600,\"timezone\":\"Europe/Budapest\",\"timezone_abbreviation\":\"CET\",\"elevation\":124.0,\"hourly_units\":{\"time\":\"iso8601\",\"temperature_2m\":\"°C\"},\"hourly\":{\"time\":[\"2024-03-20T00:00\",\"2024-03-20T01:00\",\"2024-03-20T02:00\",\"2024-03-20T03:00\",\"2024-03-20T04:00\",\"2024-03-20T05:00\",\"2024-03-20T06:00\",\"2024-03-20T07:00\",\"2024-03-20T08:00\",\"2024-03-20T09:00\",\"2024-03-20T10:00\",\"2024-03-20T11:00\",\"2024-03-20T12:00\",\"2024-03-20T13:00\",\"2024-03-20T14:00\",\"2024-03-20T15:00\",\"2024-03-20T16:00\",\"2024-03-20T17:00\",\"2024-03-20T18:00\",\"2024-03-20T19:00\",\"2024-03-20T20:00\",\"2024-03-20T21:00\",\"2024-03-20T22:00\",\"2024-03-20T23:00\"],\"temperature_2m\":[5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0]}}")

        var testService = WeatherService(testWeatherClient)
        var result = testService.getDailyAverage(testString)

        assertEquals(1, result.size)
        assertNotNull(result[testDate])
        assertEquals(5.0f, result[testDate])
    }

    @Test
    fun getDailyAverageEmpty() {
        var testString = "test"
        val testWeatherClient = Mockito.mock(HttpClient::class.java)
        Mockito.`when`(testWeatherClient.get(testString)).thenReturn("{}")

        var testService = WeatherService(testWeatherClient)
        try {
            testService.getDailyAverage(testString)
            fail("Should have thrown MissingKotlinParameterException")
        } catch (ex : MissingKotlinParameterException) {
            //OK
        } catch (ex : Exception) {
            fail("Should have thrown MissingKotlinParameterException, but got another one")
        }
    }
}