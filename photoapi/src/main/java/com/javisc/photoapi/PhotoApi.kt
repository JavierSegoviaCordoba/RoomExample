package com.javisc.photoapi

import com.javisc.photoapi.di.modules
import com.javisc.photoapi.repository.PhotoApiRepo
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.koinApplication

class PhotoApi : PhotoApiKoinComponent() {

    private val photoApiRepo by inject<PhotoApiRepo>()

    suspend fun getPhoto(id: Int) = photoApiRepo.getPhoto(id)

}

abstract class PhotoApiKoinComponent : KoinComponent {

    companion object {
        val koinApp = koinApplication { modules(modules) }.koin
    }

    override fun getKoin(): Koin = koinApp

}