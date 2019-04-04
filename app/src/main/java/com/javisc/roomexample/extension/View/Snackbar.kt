import android.view.View
import com.google.android.material.snackbar.Snackbar


inline fun View.snackbarShortOnDismissed(message: String, crossinline onDismissed: () -> Unit) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackbar.onDismissed { onDismissed() }
    snackbar.show()
}


inline fun Snackbar.onDismissed(crossinline onDismissed: () -> Unit) {
    addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            onDismissed()
        }
    })
}

