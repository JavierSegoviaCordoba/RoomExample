package com.javisc.roomexample.repository

sealed class RepoStatus {
    sealed class ERROR : RepoStatus() {
        class API(val error: String) : RepoStatus()
        class DB(val error: String) : RepoStatus()
    }

    object LOADING : RepoStatus()
    object SUCCESS : RepoStatus()
}