package com.teamwork.android.model

import java.io.Serializable

data class ProjectsResult(

        var status: String,
        var projects: List<Project>

) : Serializable
