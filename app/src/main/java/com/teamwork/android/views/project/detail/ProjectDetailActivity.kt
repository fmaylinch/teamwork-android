package com.teamwork.android.views.project.detail

import android.os.Bundle
import com.teamwork.android.R
import com.teamwork.android.model.Project
import com.teamwork.android.views.BaseActivity
import kotlinx.android.synthetic.main.activity_project_detail.*

/**
 * Displays details of a [Project].
 */
class ProjectDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)
        setSupportActionBar(toolbar)

        title = "Project name" // TODO: display title and all the details
    }
}
