package kz.hacknu.findroom.presentation.search_room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * created by: Makhanov Madiyar
 * on: 21.03.2021
 */
@Parcelize
class SearchResponse(
    val count: Int?,
    val data: List<SearchItem>?
) : Parcelable

@Parcelize
class SearchItem(
    val id: Long?,
    val lat: Double?,
    val long: Double?,
    val title: String?,
    val link: String?,
    val createdAt: Long?,
    val left: Long?,
    val tags: String?
) : Parcelable