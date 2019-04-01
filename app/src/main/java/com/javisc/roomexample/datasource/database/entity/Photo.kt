package com.javisc.roomexample.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    val albumId: Long,
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)