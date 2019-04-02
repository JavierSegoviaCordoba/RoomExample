package com.javisc.roomexample.datasource.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javisc.roomexample.datasource.database.entity.Photo

@Dao
interface PhotoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM Photo WHERE id = :id")
    fun getPhoto(id: Long): LiveData<Photo>

    @Query("SELECT * FROM Photo")
    fun getAll(): LiveData<List<Photo>>

    @Query("DELETE FROM Photo")
    suspend fun deleteAll()

}