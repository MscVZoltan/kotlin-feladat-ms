package hu.vanio.kotlin.feladat.ms

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*
import kotlin.collections.HashMap

class WeatherService (var mClient : HttpClient) {

    private fun calc(data: WeatherData) : Map<Date, Float> {
        val dailyMap = HashMap<Date, Float>()
        data.hourly.time.zip(data.hourly.temperature_2m).forEach {
            dailyMap[it.first] = dailyMap[it.first]?.plus(it.second) ?: it.second
        }
        val ret = dailyMap.mapValues { it.value / 24 } //TODO: most feltételezzük, hogy óránkénti adatok vannak, a számunkra szükséges időzóna szerint
        return ret.toSortedMap()
    }
    fun getDailyAverage(url: String): Map<Date, Float> {
        val response = mClient.get(url)
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue<WeatherData>(response)
        return calc(data)
    }
}