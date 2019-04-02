package com.javisc.roomexample.util

sealed class Status {
    sealed class ERROR : Status() {
        class API(val error: String) : Status()
        class DB(val error: String) : Status()
    }

    object LOADING : Status()
    object SUCCESS : Status()
}