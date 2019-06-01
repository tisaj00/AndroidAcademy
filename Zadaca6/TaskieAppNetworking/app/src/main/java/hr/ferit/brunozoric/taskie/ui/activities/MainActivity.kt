package hr.ferit.brunozoric.taskie.ui.activities.base

import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.activities.base.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.fragments.TasksFragment


class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
    }

}