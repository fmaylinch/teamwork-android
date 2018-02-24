package com.teamwork.android.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.teamwork.android.ProjectApp
import com.teamwork.android.R
import com.teamwork.android.util.RxUtil
import rx.Observable
import java.io.Serializable

abstract class BaseActivity<T:Serializable> : AppCompatActivity() {

    private var extraArg: T? = null
    protected val api = ProjectApp.instance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadExtra(savedInstanceState)
    }

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


    // Extras handling. Takes care of recovering/saving/restoring argument.

    companion object {
        val extraArgKey = "extra-arg"
    }

    /** Gets the argument that was passed using [start]. */
    fun getArgument(): T? {
        return extraArg
    }

    private fun loadExtra(state: Bundle?) {
        extraArg = getExtras(state)?.getSerializable(extraArgKey) as T?
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(extraArgKey, extraArg)
    }

    /**
     * Returns the appropriate extras [Bundle].
     * The given bundle or the intent.extras by default.
     */
    protected fun getExtras(bundle: Bundle?): Bundle? {
        return bundle ?: intent.extras
    }
}

/**
 * Dummy class for activities that don't receive an (extra) argument.
 */
class NoArg : Serializable