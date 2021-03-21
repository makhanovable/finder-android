package kz.hacknu.findroom.presentation.interest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.hacknu.findroom.data.repo.MainRepo
import kz.hacknu.findroom.data.room.Interests

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class InterestsViewModel(
    val repo: MainRepo
) : ViewModel() {

    val pairedList = MutableLiveData<ArrayList<Pair<Boolean, Interests>>>()

    fun start(list: List<Interests>?) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getInterests {
                pairedList.postValue(toPairedList(it, list))
            }
        }
    }

    private fun toPairedList(
        list: List<Interests>,
        selected: List<Interests>?
    ): ArrayList<Pair<Boolean, Interests>> {
        val res = arrayListOf<Pair<Boolean, Interests>>()
        for (item in list) {
            res.add(Pair(isChecked(selected, item), item))
        }
        return res
    }

    private fun isChecked(list: List<Interests>?, eRef: Interests): Boolean {
        if (list == null) return false
        for (item in list) {
            if (item.idLocal == eRef.idLocal) {
                return true
            }
        }
        return false
    }
}