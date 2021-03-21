package kz.hacknu.findroom.data.repo

import kz.hacknu.findroom.data.room.Interests
import kz.hacknu.findroom.network.FindRoomService
import kz.hacknu.findroom.presentation.create_room.ToCreate
import kz.hacknu.findroom.presentation.search_room.SearchResponse
import kz.hacknu.findroom.presentation.search_room.ToSearch
import retrofit2.Response

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class MainRepoImpl(private val service: FindRoomService) : MainRepo {
    override suspend fun getInterests(res: (list: List<Interests>) -> Unit) {
        val list = arrayListOf<Interests>()
        list.add(Interests(0, "Игра Мафия"))
        list.add(Interests(1, "Пообщаться"))
        list.add(Interests(2, "Порно группа"))

        res.invoke(list)
    }

    override suspend fun createRoom(toCreate: ToCreate): Response<Any> {
        return service.createRoom(toCreate)
    }

    override suspend fun searchRooms(toSearch: ToSearch): Response<SearchResponse> {
        return service.search(toSearch)
    }
}