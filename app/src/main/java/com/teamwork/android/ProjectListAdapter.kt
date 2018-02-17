package com.teamwork.android

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    var projects: List<String> = emptyList() // TODO: create Project model class

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = TextView(parent?.context) // TODO: inflate layout
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (holder != null) {
            holder.text.text = projects[position] // TODO: display more data
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text: TextView = view as TextView // TODO: keep more data
    }
}