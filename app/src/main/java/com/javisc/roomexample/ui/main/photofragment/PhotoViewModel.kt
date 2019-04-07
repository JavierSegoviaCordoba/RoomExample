package com.javisc.roomexample.ui.main.photofragment

import androidx.lifecycle.*
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.repository.RepoStatus
import com.javisc.roomexample.ui.ScreenState
import kotlinx.coroutines.launch

class PhotoViewModel(private val photoRepo: PhotoRepo) : ViewModel() {

    private val _screenStates = MutableLiveData<ScreenState>()
    val screenState = Transformations.switchMap(photoRepo.getRepoStatus()) {
        when (it) {
            is RepoStatus.SUCCESS -> _screenStates.value = ScreenState.SUCCESS
            is RepoStatus.ERROR.API -> _screenStates.value = ScreenState.ERROR(it.error)
            is RepoStatus.ERROR.DB -> _screenStates.value = ScreenState.ERROR(it.error)
            is RepoStatus.LOADING -> _screenStates.value = ScreenState.LOADING
        }.let { _screenStates }
    }

    fun finishState() {
        _screenStates.value = ScreenState.FINISHED
    }

    val photoList: LiveData<List<Photo>> = photoRepo.getPhotos()

    fun getPhoto(id: Int) = viewModelScope.launch { photoRepo.fetchPhoto(id) }

    fun clear() = viewModelScope.launch { photoRepo.clear() }

}
