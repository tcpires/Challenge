package com.desafio.challenge.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.desafio.domain.interactor.PostalCodeUseCase
import com.desafio.domain.util.CoroutineContextProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::PostalCodeUseCase)
    singleOf(::CoroutineContextProvider)
}