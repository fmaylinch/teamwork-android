package com.teamwork.android.views.project.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.teamwork.android.R
import com.teamwork.android.model.Project
import com.teamwork.android.model.TaskListsResult
import com.teamwork.android.views.BaseActivity
import kotlinx.android.synthetic.main.activity_project_detail.*
import kotlinx.android.synthetic.main.content_project_detail.*

/**
 * Displays details of a [Project].
 */
class ProjectDetailActivity : BaseActivity<Project>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_project_detail)
        setSupportActionBar(toolbar)

        val project = getArgument()
        title = project.name

        setupViews()
        loadTaskLists(project)
    }

    private fun setupViews() {

        val adapter = ProjectDetailAdapter()
        project_details_list.adapter = adapter
        project_details_list.layoutManager = LinearLayoutManager(this)
    }

    private fun loadTaskLists(project: Project) {

        api.taskLists(project.id)
                .compose(forUi())
                .subscribe(
                        { displayTaskLists(it) },
                        { displayNetworkError(it) })
    }

    private fun displayTaskLists(result: TaskListsResult) {

        (project_details_list.adapter as ProjectDetailAdapter).taskLists = result.tasklists
        project_details_list.adapter.notifyDataSetChanged()
    }
}
