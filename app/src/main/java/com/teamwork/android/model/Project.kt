package com.teamwork.android.model

import java.io.Serializable

data class Project(

        var name: String,
        var company: Company

) : Serializable
