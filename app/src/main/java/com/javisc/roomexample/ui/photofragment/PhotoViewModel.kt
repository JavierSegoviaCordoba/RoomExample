package com.javisc.roomexample.ui.photofragment

import androidx.lifecycle.*
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.util.ScreenState
import com.javisc.roomexample.util.Status
import kotlinx.coroutines.launch

class PhotoViewModel(private val photoRepo: PhotoRepo) : ViewModel() {

    private val _screenStates = MutableLiveData<ScreenState>()
    val screenState = Transformations.switchMap(photoRepo.status) {
        when (it) {
            is Status.SUCCESS -> _screenStates.value = ScreenState.SUCCESS
            is Status.ERROR.API -> _screenStates.value = ScreenState.ERROR(it.error)
            is Status.ERROR.DB -> _screenStates.value = ScreenState.ERROR(it.error)
            is Status.LOADING -> _screenStates.value = ScreenState.LOADING
        }.let { _screenStates }
    }

    fun finishState() {
        _screenStates.value = ScreenState.FINISHED
    }

    val photoList: LiveData<List<Photo>> = photoRepo.getPhotos()

    fun getPhoto(id: Int) = viewModelScope.launch { photoRepo.fetchPhoto(id) }

    fun clear() = viewModelScope.launch { photoRepo.clear() }

}
