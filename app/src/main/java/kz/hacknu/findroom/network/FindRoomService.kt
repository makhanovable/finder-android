package kz.hacknu.findroom.network

import kz.hacknu.findroom.presentation.create_room.ToCreate
import kz.hacknu.findroom.presentation.search_room.SearchResponse
import kz.hacknu.findroom.presentation.search_room.ToSearch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
interface FindRoomService {
    @POST("create")
    suspend fun createRoom(@Body body: ToCreate): Response<Any>

    @POST("get-event")
    suspend fun search(@Body body: ToSearch): Response<SearchResponse>
}