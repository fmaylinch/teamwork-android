package com.teamwork.android.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TasksResult(

        var status: String,

        @SerializedName(value="todo-items")
        var items: List<Task>

) : Serializable
