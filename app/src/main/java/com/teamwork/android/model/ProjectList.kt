package com.teamwork.android.model

import java.io.Serializable

data class ProjectList (

        var status: String,
        var projects: List<Project>

) : Serializable
