package com.teamwork.android.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.teamwork.android.ProjectApp
import com.teamwork.android.R
import com.teamwork.android.util.RxUtil
import com.teamwork.android.views.project.detail.ProjectDetailActivity
import rx.Observable
import java.io.Serializable

abstract class BaseActivity<T:Serializable> : AppCompatActivity() {

    protected val api = ProjectApp.instance.api

    /** @see RxUtil.forUi */
    protected fun <T> forUi() : Observable.Transformer<T,T> {
        return RxUtil.forUi(this)
    }

    /** Displays a generic network error */
    protected fun displayNetworkError(error: Throwable) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show()
        error.printStackTrace()
    }


    /** Easy way to start an activity with an argument. From the activity, use [getArgument]. */
    protected fun <E:Serializable, A:BaseActivity<E>> start(activityClass: Class<out A>, argument: E? = null) {
        startActivity(Intent(this, activityClass).putExtra(extraArgKey, argument))
    }

    /** Get the argument that was passed using [start]. */
    protected fun getArgument() : T {
        return intent.getSerializableExtra(extraArgKey) as T
    }

    companion object {
        val extraArgKey = "extra-arg"
    }

}

/**
 * Dummy class for activities that don't receive an (extra) argument.
 */
class NoArg : Serializable