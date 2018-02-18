package com.teamwork.android

import android.app.Application
import com.squareup.okhttp.OkHttpClient
import com.teamwork.android.api.Api
import com.teamwork.android.api.ApiConstants
import com.teamwork.android.api.ApiRequestInterceptor
import retrofit.RestAdapter
import retrofit.client.OkClient
import java.util.concurrent.TimeUnit

class ProjectApp : Application() {

    lateinit var api: Api
        private set

    override fun onCreate() {
        super.onCreate()

        // Wee keep a static reference for simplicity
        // See: http://stackoverflow.com/a/23705667
        instance = this

        setupApi()
    }

    private fun setupApi() {

        val okHttpClient = OkHttpClient()
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS)

        val builder = RestAdapter.Builder()
                .setRequestInterceptor(ApiRequestInterceptor())
                .setEndpoint(ApiConstants.apiUrl)
                .setClient(OkClient(okHttpClient))

        api = builder.build().create(Api::class.java)
    }

    companion object {
        lateinit var instance: ProjectApp
            private set
    }
}
