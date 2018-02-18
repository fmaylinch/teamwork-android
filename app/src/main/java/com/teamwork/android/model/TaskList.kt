package com.teamwork.android.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskList(

        var id: String,
        var name: String,
        @SerializedName(value="uncompleted-count")
        var numTasks: Int,
        var tasks: List<Task>?,

        /** Not part of model; it's used to know if the [Task]s must be displayed */
        var showTasks: Boolean

) : Serializable
