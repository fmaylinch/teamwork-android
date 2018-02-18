package com.teamwork.android.model

import java.io.Serializable

data class TaskListsResult(

        var status: String,
        var tasklists: List<TaskList>

) : Serializable
