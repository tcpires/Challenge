package com.desafio.challenge.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.desafio.domain.interactor.PostalCodeUseCase
import com.desafio.data.repository.PostalCodeRepositoryImpl
import com.desafio.domain.repository.PostalCodeRepository
import com.desafio.domain.util.CoroutineContextProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf

val domainModule = module {

    factoryOf(::PostalCodeUseCase)

    factoryOf(::PostalCodeRepositoryImpl) {
        bind<PostalCodeRepository>()
    }

    singleOf(::CoroutineContextProvider)
}