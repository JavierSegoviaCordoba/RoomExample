import com.google.android.material.snackbar.Snackbar

inline fun Snackbar.onDismissed(crossinline onDismissed: () -> Unit) {
    addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            onDismissed()
        }
    })
}