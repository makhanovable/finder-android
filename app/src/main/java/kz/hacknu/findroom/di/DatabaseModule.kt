package kz.hacknu.findroom.di

import kz.hacknu.findroom.data.room.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
val databaseModule = module {
    single { LocalDatabase(androidContext()).getInterestsDao() }
}