package com.javisc.roomexample.datasource.service

data class PhotoDto(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)