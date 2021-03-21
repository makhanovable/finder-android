package kz.hacknu.findroom.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
@Entity
@Parcelize
data class Interests(
    @PrimaryKey(autoGenerate = true)
    val idLocal: Long = 0,
    val title: String
) : Parcelable
