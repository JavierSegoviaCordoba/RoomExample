package com.javisc.roomexample.di

import com.javisc.photoapi.PhotoApi
import com.javisc.roomexample.datasource.database.DatabaseRoom
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.repository.PhotoRepoImpl
import com.javisc.roomexample.ui.main.photofragment.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single<PhotoRepo> { PhotoRepoImpl(PhotoApi(), DatabaseRoom.database.photoDAO) }
    viewModel { PhotoViewModel(get()) }
}