package com.desafio.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.desafio.presentation.home.HomeViewModel

val homeModule = module{
    viewModelOf(::HomeViewModel)
}