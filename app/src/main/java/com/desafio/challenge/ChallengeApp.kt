package com.desafio.challenge

import android.app.Application
import com.desafio.challenge.di.allAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin


@Suppress("unused")
class ChallengeApp : Application() {

    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidContext(this@ChallengeApp)
            androidFileProperties()
            modules(allAppModules)
        }
    }
}