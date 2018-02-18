package com.teamwork.android

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamwork.android.model.Project

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    var projects: List<Project> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = TextView(parent?.context) // TODO: inflate layout
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (holder != null) {
            holder.text.text = projects[position].name // TODO: display more data
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text: TextView = view as TextView // TODO: keep more data
    }
}