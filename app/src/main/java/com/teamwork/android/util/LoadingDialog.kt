package com.teamwork.android.util

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import com.teamwork.android.R

/**
 * Dialog to display while loading (e.g. network requests).
 *
 * Based on: http://stackoverflow.com/a/3226233/1121497
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.CustomDialog) {

    init {
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
        this.addContentView(ProgressBar(context), LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
    }

    companion object {

        @JvmStatic
        fun safeDismiss(dialog: Dialog?) {

            try {
                dialog?.dismiss()
            } catch (e: RuntimeException) {
                // Ignore, the activity showing the dialog may have finished.
                // See: http://stackoverflow.com/a/5102572/1121497
            }
        }
    }
}