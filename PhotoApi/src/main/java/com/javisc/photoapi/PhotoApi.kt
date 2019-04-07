package com.javisc.photoapi

import com.javisc.photoapi.di.modules
import com.javisc.photoapi.repository.PhotoApiRepo
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject

class PhotoApi : KoinComponent {

    companion object {
        init {
            loadKoinModules(modules)
        }
    }

    private val photoApiRepo by inject<PhotoApiRepo>()

    suspend fun getPhoto(id: Int) = photoApiRepo.getPhoto(id)

}
