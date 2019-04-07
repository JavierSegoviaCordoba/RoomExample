package com.javisc.roomexample.ui

sealed class ScreenState {
    class ERROR(val message: String) : ScreenState()
    object LOADING : ScreenState()
    object FINISHED : ScreenState()
    object SUCCESS : ScreenState()
}