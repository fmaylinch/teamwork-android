package com.teamwork.android

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.teamwork.android.model.ProjectList
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.content_project_list.*
import rx.android.schedulers.AndroidSchedulers.mainThread

class ProjectListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)
        setSupportActionBar(toolbar)

        setupViews()
        loadProjects()
    }

    private fun setupViews() {

        project_list.adapter = ProjectListAdapter()
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
}
