package com.example.gd_room_a_10627.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gd_room_a_10627.room.Note

@Database(
    entities = [Note::class],
    version = 1
)

abstract class NoteDB: RoomDatabase() {

    abstract fun noteDao() : NoteDao
    companion object {
        @Volatile private var instance : NoteDB? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "note12345.db"
            ).build()
    }

}