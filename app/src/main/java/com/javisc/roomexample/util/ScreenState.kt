package com.javisc.roomexample.util

sealed class ScreenState {
    sealed class ERROR {
        class API(val error: String) : ScreenState()
        class DATABASE(val error: String) : ScreenState()
    }

    object LOADING : ScreenState()
    object SUCCESS : ScreenState()
}

