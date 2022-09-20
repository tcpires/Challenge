package com.desafio.challenge.di

import com.desafio.data.remote.FileDownloaderClient
import com.desafio.data.repository.FileDownloaderImpl
import org.koin.dsl.module

val dataModule = module {
    single<FileDownloaderClient> {
        FileDownloaderImpl()
    }
}