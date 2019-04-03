package com.javisc.roomexample.extension.View

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbarShort(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
fun View.snackbarLong(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
fun View.snackbarIndefinite(message: String) =
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).show()

