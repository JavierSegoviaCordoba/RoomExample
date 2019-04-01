package com.javisc.roomexample.datasource.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.javisc.roomexample.App.Companion.appContext
import com.javisc.roomexample.datasource.database.dao.PhotoDAO
import com.javisc.roomexample.datasource.database.entity.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class DatabaseRoom : RoomDatabase() {

    companion object {
        val database = Room.databaseBuilder(appContext, DatabaseRoom::class.java, "Companies")
            .fallbackToDestructiveMigration().build()
    }

    abstract val photoDAO: PhotoDAO

}