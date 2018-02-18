package com.teamwork.android.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskList(

        var name: String,
        @SerializedName(value="uncompleted-count")
        var numTasks: Int,
        var tasks: List<Task>?

) : Serializable
