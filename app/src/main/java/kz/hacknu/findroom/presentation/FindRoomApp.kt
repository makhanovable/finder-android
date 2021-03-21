package kz.hacknu.findroom.presentation

import android.app.Application
import android.content.Context
import kz.hacknu.findroom.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class FindRoomApp : Application() {

    companion object {
        lateinit var CONTEXT: Context
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this

        startKoin {
            androidContext(this@FindRoomApp)
            modules(
                listOf(
                    databaseModule,
                    dataModule,
                    networkModule,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }
}