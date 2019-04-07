package com.javisc.roomexample.ui.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar
import onDismissed

inline fun View.snackbarShortOnDismissed(message: String, crossinline onDismissed: () -> Unit) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackbar.onDismissed { onDismissed() }
    snackbar.show()
}
