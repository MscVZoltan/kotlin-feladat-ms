package hu.vanio.kotlin.feladat.ms

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpClient {
    fun get(url: String) : String {
        var client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw IOException("Unexpected code $it")
            }
            return it.body!!.string()
        }
    }
}