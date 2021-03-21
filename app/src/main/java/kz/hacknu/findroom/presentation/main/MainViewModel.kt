package kz.hacknu.findroom.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.hacknu.findroom.data.repo.MainRepo
import kz.hacknu.findroom.presentation.search_room.SearchItem
import kz.hacknu.findroom.presentation.search_room.SearchResponse

/**
 * created by: Makhanov Madiyar
 * on: 21.03.2021
 */
class MainViewModel(private val repo: MainRepo): ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<Any>()
    val close = MutableLiveData<Boolean>()
    val searchResult = MutableLiveData<SearchResponse>()
}