package hu.vanio.kotlin.feladat.ms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.text.DateFormat


@SpringBootApplication
class WeatherApp

fun main() {
    runApplication<WeatherApp>()
    try {
        var service = WeatherService(HttpClient())
        var result = service.getDailyAverage("https://api.open-meteo.com/v1/forecast?latitude=47.4984&longitude=19.0404&hourly=temperature_2m&timezone=auto")

        val df = DateFormat.getDateInstance()
        result.forEach { entry ->
            print("Day: ${df.format(entry.key)} - Average: ${entry.value}\n")
        }
    } catch (ex : Exception) {
        print("Exception occured: ${ex.message}")
    }
}

