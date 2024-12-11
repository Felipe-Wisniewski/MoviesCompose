package com.wisnitech.data.remote.retrofit

import io.nerdythings.okhttp.profiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.wisnitech.data.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl: String = BuildConfig.BASE_URL
private const val bearToken: String = BuildConfig.BEAR_TOKEN
private const val apiKey: String = BuildConfig.API_KEY

object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder().apply {
        addNetworkInterceptor { chain ->
            var original = chain.request()
            val originalUrl = original.url()

            val url = originalUrl.newBuilder()
                .build()

            original = original.newBuilder()
                .url(url)
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer $bearToken")
                .build()

            chain.proceed(original)
        }

        if (BuildConfig.DEBUG) addInterceptor(OkHttpProfilerInterceptor())
    }


    @PublishedApi
    internal val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    inline fun <reified T> generate(): T {
        return try {
            retrofit.create(T::class.java)
        } catch (e: Exception) {
            throw Exception("")
        }
    }

}