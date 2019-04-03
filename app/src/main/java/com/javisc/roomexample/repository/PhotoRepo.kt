package com.javisc.roomexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.javisc.roomexample.datasource.database.DatabaseRoom
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.datasource.database.entity.toEntity
import com.javisc.roomexample.datasource.service.PhotoApi
import com.javisc.roomexample.datasource.service.PhotoDto
import com.javisc.roomexample.util.Status
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoRepo : KoinComponent {

    private val dao = DatabaseRoom.database.photoDAO
    private val photoApi by inject<PhotoApi>()

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    suspend fun fetchPhoto(id: Int) {
        _status.value = Status.LOADING

        val photo: Either<Throwable, PhotoDto> = photoApi.getPhoto(id)
        photo.fold({
            _status.value = Status.ERROR.API("API ERROR")
        }, {
            try {
                dao.insert(it.toEntity())
                _status.value = Status.SUCCESS
            } catch (exception: Exception) {
                _status.value = Status.ERROR.DB("DB ERROR")
            }
        })
    }

    fun getPhotos(): LiveData<List<Photo>> = dao.getAll()

    suspend fun clear() = dao.deleteAll()

}