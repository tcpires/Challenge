package com.desafio.challenge.di

import com.desafio.challenge.di.Properties.BASE_URL
import com.desafio.data.remote.Service
import com.desafio.data.remote.config.RetrofitConfig
import org.koin.dsl.module

val dataModule = module {

    single<Service> {
        RetrofitConfig.create(
            baseUrl = "https://raw.githubusercontent.com/centraldedados/codigos_postais/"
//            getProperty(BASE_URL)
        )
    }
}