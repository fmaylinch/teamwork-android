package com.teamwork.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.content_project_list.*

class ProjectListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)
        setSupportActionBar(toolbar)
        setupViews()
    }

    private fun setupViews() {

        project_list.adapter = ProjectListAdapter()
        project_list.layoutManager = LinearLayoutManager(this)

        // TODO: get projects from API
        (project_list.adapter as ProjectListAdapter).projects = listOf("some project", "another project")
        project_list.adapter.notifyDataSetChanged()
    }
}
