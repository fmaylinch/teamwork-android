package com.teamwork.android.views.project.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamwork.android.model.TaskList

/**
 * Displays [TaskList]s (one in each section).
 * The section header displays the [TaskList] name and
 * the section items display the [Task] of that [TaskList].
 */
class ProjectDetailAdapter : SectionedRecyclerViewAdapter<ProjectDetailAdapter.Holder>() {

    var taskLists: List<TaskList> = emptyList()

    enum class ViewType {
        TASK_LIST_HEADER, TASK_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val type = ViewType.values()[viewType]

        return when (type) {
            ViewType.TASK_LIST_HEADER ->
                Holder(TextView(parent.context)) // TODO: inflate view
            ViewType.TASK_ITEM ->
                Holder(TextView(parent.context)) // TODO: inflate view
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

    override fun onBindViewHolder(vh: Holder, coord: ItemCoord) {

        val textView = vh.itemView as TextView

        when (getViewType(coord)) {
            ViewType.TASK_LIST_HEADER ->
                textView.text = taskLists[coord.section].name
            ViewType.TASK_ITEM ->
                textView.text = taskLists[coord.section].tasks?.get(coord.index)?.name
        }
    }

    private fun getViewType(coord: ItemCoord) : ViewType {
        return if (coord.header) ViewType.TASK_LIST_HEADER else ViewType.TASK_ITEM
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        // TODO: add more details
    }
}