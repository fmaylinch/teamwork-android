package com.teamwork.android.model

import java.io.Serializable

data class Project(

        var id: String,
        var name: String,
        var company: Company,
        var starred: Boolean,
        var status: String,
        var description: String

) : Serializable
