package com.teamwork.android

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity() {

    protected val api = ProjectApp.instance.api

    protected fun displayError(error: Throwable?) {
        // TODO: Improve error
        val text = "Connection error"
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}