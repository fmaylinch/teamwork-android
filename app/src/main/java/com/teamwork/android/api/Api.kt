package com.teamwork.android.api

import com.teamwork.android.model.ProjectList

import retrofit.http.GET
import rx.Observable

interface Api {

    @GET("/projects.json")
    fun projectList() : Observable<ProjectList>
}
