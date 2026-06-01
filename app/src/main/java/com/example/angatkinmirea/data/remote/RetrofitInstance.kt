package com.example.angatkinmirea.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun createApi(
        tokenProvider: () -> String?
    ): ApiService {

        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY

        val client =
            OkHttpClient.Builder()
                .addInterceptor(
                    AuthInterceptor(tokenProvider)
                )
                .addInterceptor(logging)
                .build()

        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}