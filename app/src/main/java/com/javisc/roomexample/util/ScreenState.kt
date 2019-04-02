package com.javisc.roomexample.util

sealed class ScreenState {
    class ERROR(val message: String) : ScreenState()
    object LOADING : ScreenState()
    object SUCCESS : ScreenState()
}