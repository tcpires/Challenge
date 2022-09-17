package com.desafio.challenge.di

import com.desafio.data.remote.config.JacksonConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
    single<String>(named(name = Properties.BASE_URL)){getProperty(Properties.BASE_URL)}
//    single{JacksonConfig.createObjectMapper()}
}