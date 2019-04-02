package com.javisc.roomexample.view.photofragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javisc.roomexample.repository.PhotoRepo
import kotlinx.coroutines.launch

class PhotoViewModel(private val photoRepo: PhotoRepo) : ViewModel() {

    val screenState = photoRepo.status
    val photoList = photoRepo.getPhotos()

    fun getPhoto(id: Long) = viewModelScope.launch { photoRepo.fetchPhoto(id) }

    fun clear() = viewModelScope.launch { photoRepo.clear() }

}
