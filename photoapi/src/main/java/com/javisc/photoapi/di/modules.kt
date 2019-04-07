package com.javisc.photoapi.di

import com.javisc.photoapi.datasource.PhotoApiServiceBuilder
import com.javisc.photoapi.repository.PhotoApiRepo
import com.javisc.photoapi.repository.PhotoApiRepoImpl
import org.koin.dsl.module

val modules = module {
    single<PhotoApiRepo> { PhotoApiRepoImpl(PhotoApiServiceBuilder.service) }
}