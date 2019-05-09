package com.javisc.roomexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.Try
import com.javisc.photoapi.PhotoApi
import com.javisc.photoapi.datasource.PhotoDto
import com.javisc.roomexample.datasource.database.dao.PhotoDAO
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.datasource.database.entity.toEntity


interface PhotoRepo {
    suspend fun insert(photo: Photo)
    suspend fun fetchPhoto(id: Int)
    fun getPhotos(): LiveData<List<Photo>>
    suspend fun clear()
    fun getRepoStatus(): LiveData<RepoStatus>
}

class PhotoRepoImpl(private val photoApi: PhotoApi, private val dao: PhotoDAO) : PhotoRepo {

    override suspend fun insert(photo: Photo) {
        val insertEither = Try { dao.insert(photo) }.toEither()
        when (insertEither) {
            is Either.Left -> {
                val throwable: Throwable = insertEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.DB(it) }
            }
            is Either.Right -> {
                _repoStatus.value = RepoStatus.SUCCESS
            }
        }
    }

    private val _repoStatus = MutableLiveData<RepoStatus>()

    override suspend fun fetchPhoto(id: Int) {
        _repoStatus.value = RepoStatus.LOADING

        val photoEither: Either<Throwable, PhotoDto> = Try { photoApi.getPhoto(id) }.toEither()
        when (photoEither) {
            is Either.Left -> {
                val throwable: Throwable = photoEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.API(it) }
            }
            is Either.Right -> {
                val photoDto: PhotoDto = photoEither.b
                insert(photoDto.toEntity())
            }
        }
    }

    override fun getPhotos(): LiveData<List<Photo>> = dao.getAll()

    override suspend fun clear() = dao.deleteAll()

    override fun getRepoStatus(): LiveData<RepoStatus> = _repoStatus
}