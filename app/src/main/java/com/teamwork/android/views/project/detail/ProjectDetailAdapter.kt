package com.teamwork.android.views.project.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamwork.android.ProjectApp
import com.teamwork.android.R
import com.teamwork.android.model.TaskList
import com.teamwork.android.util.inflate
import kotlinx.android.synthetic.main.view_task_list_header.view.*

/**
 * Displays [TaskList]s (one in each section).
 * The section header displays the [TaskList] name and
 * the section items display the [Task] of that [TaskList].
 */
class ProjectDetailAdapter : SectionedRecyclerViewAdapter<RecyclerView.ViewHolder>() {

    var taskLists: List<TaskList> = emptyList()

    enum class ViewType {
        TASK_LIST_HEADER, TASK_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val type = ViewType.values()[viewType]

        return when (type) {
            ViewType.TASK_LIST_HEADER ->
                TaskListHeaderHolder(parent.inflate(R.layout.view_task_list_header))
            ViewType.TASK_ITEM ->
                TaskItemHolder(TextView(parent.context)) // TODO: inflate view
        }
    }

    override fun isHeaderDisplayed(section: Int): Boolean {
        return true // always display task list header
    }

    override fun getSectionCount() = taskLists.size

    override fun getItemCount(section: Int) = taskLists[section].tasks?.size ?: 0

    override fun getItemViewType(coord: ItemCoord): Int {
        return getViewType(coord).ordinal
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, coord: ItemCoord) {

        val taskList = taskLists[coord.section]

        when (getViewType(coord)) {

            ViewType.TASK_LIST_HEADER -> {
                val holder = vh as TaskListHeaderHolder
                holder.name.text = taskList.name
                holder.numTasks.text = ProjectApp.instance.getString(R.string.number_of_tasks, taskList.numTasks)
            }

            ViewType.TASK_ITEM -> {
                val textView = vh.itemView as TextView
                textView.text = taskList.tasks?.get(coord.index)?.name
            }
        }
    }

    private fun getViewType(coord: ItemCoord) : ViewType {
        return if (coord.header) ViewType.TASK_LIST_HEADER else ViewType.TASK_ITEM
    }

    class TaskListHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.name
        val numTasks: TextView = view.num_tasks
    }

    class TaskItemHolder(view: View) : RecyclerView.ViewHolder(view) { // TODO

    }
}