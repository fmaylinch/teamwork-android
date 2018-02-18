package com.teamwork.android.api

import com.teamwork.android.model.ProjectsResult
import com.teamwork.android.model.TaskListsResult

import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface Api {

    @GET("/projects.json")
    fun projectList() : Observable<ProjectsResult>

    @GET("/projects/{id}/tasklists.json")
    fun taskLists(@Path("id") projectId: String) : Observable<TaskListsResult>
}
