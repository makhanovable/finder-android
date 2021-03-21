package kz.hacknu.findroom.di

import kz.hacknu.findroom.presentation.create_room.CreateRoomViewModel
import kz.hacknu.findroom.presentation.interest.InterestsViewModel
import kz.hacknu.findroom.presentation.main.MainViewModel
import kz.hacknu.findroom.presentation.search_room.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
val viewModelModule = module {
    viewModel { InterestsViewModel(get()) }
    viewModel { CreateRoomViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}