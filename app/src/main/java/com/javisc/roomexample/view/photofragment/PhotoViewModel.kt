package com.javisc.roomexample.view.photofragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.util.ScreenState
import com.javisc.roomexample.util.Status
import kotlinx.coroutines.launch


class PhotoViewModel(private val photoRepo: PhotoRepo) : ViewModel() {

    private val _screenStates = MutableLiveData<ScreenState>()
    val screenState = Transformations.switchMap(photoRepo.status) {
        when (it) {
            is Status.SUCCESS -> _screenStates.value = ScreenState.SUCCESS
            is Status.ERROR.API -> {
                _screenStates.value = ScreenState.ERROR(it.error)
                _screenStates.value = ScreenState.FINISHED
            }
            is Status.ERROR.DB -> {
                _screenStates.value = ScreenState.ERROR(it.error)
                _screenStates.value = ScreenState.FINISHED
            }
            is Status.LOADING -> _screenStates.value = ScreenState.LOADING
        }.let { _screenStates }
    }

    val photoList = photoRepo.getPhotos()

    fun getPhoto(id: Int) = viewModelScope.launch { photoRepo.fetchPhoto(id) }

    fun clear() = viewModelScope.launch { photoRepo.clear() }

}
