package com.javisc.photoapi.model

data class PhotoDto(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)