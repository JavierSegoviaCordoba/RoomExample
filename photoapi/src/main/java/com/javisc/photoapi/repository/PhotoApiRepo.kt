package com.javisc.photoapi.repository

import com.javisc.photoapi.datasource.PhotoDto
import com.javisc.photoapi.datasource.PhotoService
import retrofit2.await

interface PhotoApiRepo {
    suspend fun getPhoto(id: Int): PhotoDto
}

class PhotoApiRepoImpl(private val photoService: PhotoService) : PhotoApiRepo {
    override suspend fun getPhoto(id: Int): PhotoDto =
        photoService.getPhoto(id).await()
}