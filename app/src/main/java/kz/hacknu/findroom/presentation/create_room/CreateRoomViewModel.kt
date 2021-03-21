package kz.hacknu.findroom.presentation.create_room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.hacknu.findroom.data.repo.MainRepo

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class CreateRoomViewModel(private val repo: MainRepo) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<Any>()
    val close = MutableLiveData<Boolean>()

    fun create(
        lat: Double,
        long: Double,
        title: String,
        link: String,
        leftTime: Long,
        tags: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val toCreate = ToCreate(
                lat = lat,
                long = long,
                title = title,
                link = link,
                createdAt = System.currentTimeMillis(),
                left = leftTime,
                tags = tags
            )
            val response = repo.createRoom(toCreate)
            loading.postValue(false)
            if (response.isSuccessful) {
                toast.postValue("success")
                close.postValue(true)
            } else {
                toast.postValue(response.body())
            }
        }
    }
}