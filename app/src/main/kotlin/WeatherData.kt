package hu.vanio.kotlin.feladat.ms

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import kotlin.collections.ArrayList

data class HourlyUnits(
        var time: String,
        var temperature_2m: String
)

data class HourlyTime(
        //TODO: az időzónát ki lehetne szedni a JSON-ből és utólag átalakítani, de nekem most jó így...
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Budapest")
        var time: ArrayList<Date>,
        var temperature_2m: ArrayList<Float>
)

data class WeatherData(
        var latitude: Float,
        var longitude: Float,
        var generationtime_ms: Float,
        var utc_offset_seconds: Int,
        var timezone: String,
        var timezone_abbreviation: String,
        var elevation: Float,
        var hourly_units: HourlyUnits,
        var hourly: HourlyTime
)
