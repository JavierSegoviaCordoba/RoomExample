package com.javisc.roomexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.javisc.roomexample.datasource.database.DatabaseRoom
import com.javisc.roomexample.datasource.service.PhotoApi
import com.javisc.roomexample.util.ScreenState
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoRepo : KoinComponent {

    private val dao = DatabaseRoom.database.photoDAO
    private val photoApi by inject<PhotoApi>()

    private val _screenStateMutableLiveData = MutableLiveData<ScreenState>()
    val screenStateLiveData: LiveData<ScreenState> = _screenStateMutableLiveData

    suspend fun fetchPhoto(id: Long) {
        _screenStateMutableLiveData.value = ScreenState.LOADING
        val photo = photoApi.getPhoto(id)
        photo.fold(
            { _screenStateMutableLiveData.value = ScreenState.ERROR.API("API Error") },
            {
                dao.insert(it)
                _screenStateMutableLiveData.value = ScreenState.SUCCESS
            })
    }

    fun getPhotos() = dao.getAll()

    suspend fun clear() = dao.deleteAll()

}

