package com.teamwork.android.views.project.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.teamwork.android.R
import com.teamwork.android.model.Project
import com.teamwork.android.views.BaseActivity
import com.teamwork.android.model.ProjectList
import com.teamwork.android.views.NoArg
import com.teamwork.android.views.project.detail.ProjectDetailActivity
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.content_project_list.*
import rx.android.schedulers.AndroidSchedulers.mainThread

/**
 * List of [Project]s.
 * When one is clicked [ProjectDetailActivity] is opened.
 */
class ProjectListActivity : BaseActivity<NoArg>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_project_list)
        setSupportActionBar(toolbar)

        setupViews()
        loadProjects()
    }

    private fun setupViews() {

        val adapter = ProjectListAdapter()
        adapter.onItemClick = { displayProject(it) }
        project_list.adapter = adapter
        project_list.layoutManager = LinearLayoutManager(this)
    }

    private fun loadProjects() {

        api.projectList()
                .compose(forUi())
                .observeOn(mainThread())
                .subscribe(
                        { displayProjects(it) },
                        { displayNetworkError(it) })
    }

    private fun displayProjects(projectList: ProjectList?) {

        if (projectList != null) {
            (project_list.adapter as ProjectListAdapter).projects = projectList.projects
            project_list.adapter.notifyDataSetChanged()
        }
    }

    private fun displayProject(project: Project) {

        start(ProjectDetailActivity::class.java, project)
    }
}
