package com.teamwork.android

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.teamwork.android.util.RxUtil
import rx.Observable

abstract class BaseActivity : AppCompatActivity() {

    protected val api = ProjectApp.instance.api

    protected fun <T> forUi() : Observable.Transformer<T,T> {
        return RxUtil.forUi(this)
    }

    /** Displays a generic network error */
    protected fun displayNetworkError(error: Throwable) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show()
        error.printStackTrace()
    }
}