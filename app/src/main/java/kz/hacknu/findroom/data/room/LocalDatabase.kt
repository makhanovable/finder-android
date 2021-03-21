package kz.hacknu.findroom.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Interests::class
    ],
    version = 1
)

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getInterestsDao(): InterestsDao

    companion object {
        @Volatile
        private var instance: LocalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: buildDatabase(
                    context
                )
                    .also { instance = it }
        }

        private fun buildDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "find_room.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}