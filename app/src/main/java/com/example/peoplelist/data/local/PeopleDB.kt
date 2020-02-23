package com.example.peoplelist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peoplelist.data.local.room.dao.PeopleDao
import com.example.peoplelist.data.local.room.model.RoomPerson
import com.example.peoplelist.data.local.room.model.RoomPersonLike

@Database(
    entities = [
        RoomPerson::class,
        RoomPersonLike::class
    ],
    version = 1
)
abstract class PeopleDB : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    companion object {

        private const val DATABASE_NAME = "people.db"
        @Volatile
        private var instance: PeopleDB? = null

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context, PeopleDB::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

        fun getInstance(context: Context): PeopleDB {
            if (instance == null) {
                synchronized(PeopleDB::class) { instance =
                    buildDatabase(context)
                }
            }
            return instance!!
        }
    }
}