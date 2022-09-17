package com.desafio.data.remote.config

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object RetrofitConfig {

    inline fun <reified T> create(
//        objectMapper: ObjectMapper,
        baseUrl: String,
    ): T {
        return Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(T::class.java)
    }
}