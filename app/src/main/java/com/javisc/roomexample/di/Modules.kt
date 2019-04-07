package com.javisc.roomexample.di

import com.javisc.photoapi.PhotoApi
import com.javisc.roomexample.datasource.database.DatabaseRoom
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.repository.PhotoRepoImpl
import com.javisc.roomexample.ui.main.photofragment.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { PhotoApi() }
    single { DatabaseRoom.database.photoDAO }
    single<PhotoRepo> { PhotoRepoImpl(get(), get()) }
    viewModel { PhotoViewModel(get()) }
}