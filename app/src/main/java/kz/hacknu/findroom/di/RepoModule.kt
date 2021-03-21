package kz.hacknu.findroom.di

import kz.hacknu.findroom.data.repo.MainRepo
import kz.hacknu.findroom.data.repo.MainRepoImpl
import org.koin.dsl.module

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
val repoModule = module {
    single { MainRepoImpl(get()) as MainRepo }
}