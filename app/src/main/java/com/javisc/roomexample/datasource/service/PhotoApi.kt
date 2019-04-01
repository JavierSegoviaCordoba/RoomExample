package com.javisc.roomexample.datasource.service

import arrow.core.Either
import arrow.core.Try
import com.javisc.roomexample.datasource.database.entity.Photo
import retrofit2.await

interface PhotoApi {
    suspend fun getPhoto(id: Long): Either<Throwable, Photo>
}

class PhotoApiImpl(private val photoService: PhotoService) : PhotoApi {
    override suspend fun getPhoto(id: Long) =
        Try {
            println(photoService.getPhoto(id).request().url())
            photoService.getPhoto(id).await()
        }.toEither()
}