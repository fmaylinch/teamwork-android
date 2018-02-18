package com.teamwork.android.model

import java.io.Serializable

data class TaskList(

        var name: String,
        var tasks: List<Task>?

) : Serializable
