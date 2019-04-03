import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.snackbarShortOnDismiss(message: String, onDismiss: () -> Any) =
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).addCallback(object :
        BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            onDismiss()
        }
    }).show()