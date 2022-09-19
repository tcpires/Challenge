package com.desafio.challenge.di

import com.desafio.presentation.di.homeModule

val allAppModules = listOf(
    appModule,
    dataModule,
    domainModule,
    homeModule
)