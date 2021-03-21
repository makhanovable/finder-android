package kz.hacknu.findroom.presentation.search_room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * created by: Makhanov Madiyar
 * on: 21.03.2021
 */
@Parcelize
class ToSearch(
    val lat: Double,
    val long: Double,
    val radius: Long,
    val tags: String
): Parcelable