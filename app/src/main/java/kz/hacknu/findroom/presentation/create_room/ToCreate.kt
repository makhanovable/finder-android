package kz.hacknu.findroom.presentation.create_room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
@Parcelize
data class ToCreate(
    val lat: Double,
    val long: Double,
    val title: String,
    val link: String,
    val createdAt: Long,
    val left: Long,
    val tags: String
) : Parcelable