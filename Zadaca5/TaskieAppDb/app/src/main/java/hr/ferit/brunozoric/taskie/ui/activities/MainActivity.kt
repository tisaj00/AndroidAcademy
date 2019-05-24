package hr.ferit.brunozoric.taskie.ui.activities.base

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.EXTRA_SCREEN_TYPE
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.activities.base.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.AboutFragment
import hr.ferit.brunozoric.taskie.ui.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val adapter by lazy { TaskAdapter {onItemSelected(it)} }
    private val repository = TaskRoomRepository()
    private var data = repository.getTasks()

    override fun getLayoutResourceId() = R.layout.activity_main
    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
        setupListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortByPriorityMenuItem -> {
                data = repository.getTasks()
                data.sortByDescending { it.priority }
                repository.clearAllTasks()
                for(newData in data){
                    newData.taskId = null
                    repository.addTask(newData)
                }
                showFragment(TasksFragment.newInstance())
            }
            R.id.clearAllTasksMenuItem -> {
                repository.clearAllTasks()
                adapter.deleteData()
                showFragment(TasksFragment.newInstance())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupListeners() {
        bottomNav.setOnNavigationItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.action_tasks -> {
                    showFragment(TasksFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_about -> {
                    showFragment(AboutFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun onItemSelected(task: Task){
        val detailsIntent = Intent(this, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.taskId?.toInt())
        }
        startActivity(detailsIntent)
    }



}