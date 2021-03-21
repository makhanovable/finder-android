package kz.hacknu.findroom.presentation.search_room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.hacknu.findroom.data.repo.MainRepo
import kz.hacknu.findroom.presentation.create_room.ToCreate

/**
 * created by: Makhanov Madiyar
 * on: 21.03.2021
 */
class SearchViewModel(private val repo: MainRepo) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<Any>()
    val result = MutableLiveData<SearchResponse>()

    fun search(
        lat: Double,
        long: Double,
        radius: Long,
        tags: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val toSearch = ToSearch(
                lat = lat,
                long = long,
                radius = radius,
                tags = tags
            )
            val response = repo.searchRooms(toSearch)
            loading.postValue(false)
            if (response.isSuccessful) {
                toast.postValue("success")
                val res = response.body()
                if (res != null)
                    result.postValue(res)
            } else {
                toast.postValue(response.body())
            }
        }
    }

}