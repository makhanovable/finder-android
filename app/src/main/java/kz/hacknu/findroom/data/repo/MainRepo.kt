package kz.hacknu.findroom.data.repo

import kz.hacknu.findroom.data.room.Interests
import kz.hacknu.findroom.presentation.create_room.ToCreate
import kz.hacknu.findroom.presentation.search_room.SearchResponse
import kz.hacknu.findroom.presentation.search_room.ToSearch
import retrofit2.Response

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
interface MainRepo {
    suspend fun getInterests(res: (list: List<Interests>) -> Unit)
    suspend fun createRoom(toCreate: ToCreate): Response<Any>
    suspend fun searchRooms(toSearch: ToSearch): Response<SearchResponse>
}