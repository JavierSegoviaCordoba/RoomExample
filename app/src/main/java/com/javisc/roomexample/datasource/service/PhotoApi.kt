package com.javisc.roomexample.datasource.service

import arrow.core.Either
import arrow.core.Try
import retrofit2.await

interface PhotoApi {
    suspend fun getPhoto(id: Int): Either<Throwable, PhotoDto>
}

class PhotoApiImpl(private val photoService: PhotoService) : PhotoApi {
    override suspend fun getPhoto(id: Int): Either<Throwable, PhotoDto> =
        Try { photoService.getPhoto(id).await() }.toEither()
}