package com.javisc.roomexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.Try
import com.javisc.photoapi.PhotoApi
import com.javisc.photoapi.model.PhotoDto
import com.javisc.roomexample.datasource.database.dao.PhotoDAO
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.datasource.database.entity.toEntity
import org.koin.core.KoinComponent


interface PhotoRepo : KoinComponent {
    suspend fun fetchPhoto(id: Int)
    fun getPhotos(): LiveData<List<Photo>>
    suspend fun clear()
    fun getRepoStatus(): LiveData<RepoStatus>
}

class PhotoRepoImpl(private val photoApi: PhotoApi, private val dao: PhotoDAO) : PhotoRepo {

    private val _status = MutableLiveData<RepoStatus>()

    override suspend fun fetchPhoto(id: Int) {
        _status.value = RepoStatus.LOADING

        val photo: Either<Throwable, PhotoDto> = Try { photoApi.getPhoto(id) }.toEither()
        photo.fold({ throwable ->
            _status.value = throwable.message?.let { RepoStatus.ERROR.API(it) }
        }, { photoDto ->
            try {
                dao.insert(photoDto.toEntity())
                _status.value = RepoStatus.SUCCESS
            } catch (exception: Exception) {
                _status.value = exception.message?.let { RepoStatus.ERROR.DB(it) }
            }
        })
    }

    override fun getPhotos(): LiveData<List<Photo>> = dao.getAll()

    override suspend fun clear() = dao.deleteAll()

    override fun getRepoStatus(): LiveData<RepoStatus> = _status
}