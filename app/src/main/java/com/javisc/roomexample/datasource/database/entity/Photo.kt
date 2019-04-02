package com.javisc.roomexample.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javisc.roomexample.datasource.service.PhotoDto

@Entity
data class Photo(
    val albumId: Long,
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

fun PhotoDto.toEntity() = Photo(this.albumId, this.id, this.title, this.url, this.thumbnailUrl)