package com.teamwork.android.views.project.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamwork.android.ProjectApp
import com.teamwork.android.R
import com.teamwork.android.model.TaskList
import com.teamwork.android.util.inflate
import kotlinx.android.synthetic.main.view_task_item.view.*
import kotlinx.android.synthetic.main.view_task_list_header.view.*

/**
 * Displays [TaskList]s (one in each section).
 * The section header displays the [TaskList] name and
 * the section items display the [Task] of that [TaskList].
 */
class ProjectDetailAdapter : SectionedRecyclerViewAdapter<RecyclerView.ViewHolder>() {

    var taskLists: List<TaskList> = emptyList()

    /**
     * This function will be called when the [Task]s of the given [TaskList] need to be loaded.
     * Implement it to load the tasks, set them in [TaskList.tasks] and [notifyDataSetChanged] to refresh the list.
     */
    var loadTasks: ((TaskList) -> Unit)?  = null

    enum class ViewType {
        TASK_LIST_HEADER, TASK_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val type = ViewType.values()[viewType]

        return when (type) {
            ViewType.TASK_LIST_HEADER ->
                TaskListHeaderHolder(parent.inflate(R.layout.view_task_list_header))
            ViewType.TASK_ITEM ->
                TaskItemHolder(parent.inflate(R.layout.view_task_item))
        }
    }

    override fun isHeaderDisplayed(section: Int): Boolean {
        return true // always display task list header
    }

    override fun getSectionCount() = taskLists.size

    override fun getItemCount(section: Int) : Int {
        val taskList = taskLists[section]
        // Tasks are displayed if available and taskList wants to showTasks
        return if (taskList.showTasks) taskList.tasks?.size ?: 0 else 0
    }

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
                holder.itemView.setOnClickListener({
                    taskList.showTasks = !taskList.showTasks // toggle
                    if (tasksShouldBeLoaded(taskList)) {
                        loadTasks?.invoke(taskList)
                    } else {
                        notifyDataSetChanged() // to close taskList section
                    }
                })
            }

            ViewType.TASK_ITEM -> {
                val holder = vh as TaskItemHolder
                holder.content.text = taskList.tasks?.get(coord.index)?.content
            }
        }
    }

    /** Tasks should be loaded if [TaskList] wants to showTasks and they are not available yet */
    private fun tasksShouldBeLoaded(taskList: TaskList) =
            taskList.showTasks && taskList.tasks == null

    private fun getViewType(coord: ItemCoord) : ViewType {
        return if (coord.header) ViewType.TASK_LIST_HEADER else ViewType.TASK_ITEM
    }

    class TaskListHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.name
        val numTasks: TextView = view.num_tasks
    }

    class TaskItemHolder(view: View) : RecyclerView.ViewHolder(view) { // TODO

        var content: TextView = view.content
    }
}