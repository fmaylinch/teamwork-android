package com.teamwork.android

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamwork.android.model.Project
import com.teamwork.android.util.inflate
import kotlinx.android.synthetic.main.view_project_item.view.*

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    var projects: List<Project> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = parent.inflate(R.layout.view_project_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val project = projects[position]
        holder.project.text = project.name
        holder.company.text = project.company.name
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val project: TextView = view.name
        val company: TextView = view.company
    }
}
