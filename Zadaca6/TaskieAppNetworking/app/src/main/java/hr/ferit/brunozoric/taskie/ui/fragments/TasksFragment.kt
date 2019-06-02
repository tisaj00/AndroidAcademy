package hr.ferit.brunozoric.taskie.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.response.DeleteTaskResponse
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.ui.activities.base.ContainerActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener {

    private val adapter by lazy { TaskAdapter { onItemSelected(it) } }
    private val interactor = BackendFactory.getTaskieInteractor()
    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val taskToDelete = adapter.getTaskFromData(viewHolder.layoutPosition)
            interactor.deleteNote(DeleteTaskRequest(taskToDelete.id),deleteTaskCallback())
            adapter.deleteTask(taskToDelete)
        }
    }

    fun itemTouch(){
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
    }

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }

    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        itemTouch()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        getAllTasks()
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
        swipeToRefresh.setOnRefreshListener {
            getAllTasks()
            this.activity?.displayToast("Refreshed Tasks!")
            swipeToRefresh.isRefreshing=false
        }
    }

    private fun onItemSelected(task: BackendTask) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    override fun onTaskAdded(task: BackendTask){
        adapter.addData(task)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun getAllTasks() {
        progress.visible()
        interactor.getTasks(getTaskieCallback())
    }

    private fun getTaskieCallback(): Callback<GetTasksResponse> = object : Callback<GetTasksResponse> {
        override fun onFailure(call: Call<GetTasksResponse>?, t: Throwable?) {
            progress.gone()
            //TODO : handle default error
        }

        override fun onResponse(call: Call<GetTasksResponse>?, response: Response<GetTasksResponse>) {
            progress.gone()
            noData.gone()


            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(response: Response<GetTasksResponse>) {

        response.body()?.notes?.run {
            checkList(this)
            onTaskiesReceived(this)
        }
    }

    private fun deleteTaskCallback(): Callback<DeleteTaskResponse> = object : Callback<DeleteTaskResponse> {
        override fun onFailure(call: Call<DeleteTaskResponse>?, t: Throwable?) {
            Log.d("tag","didn't delete")
        }

        override fun onResponse(call: Call<DeleteTaskResponse>?, response: Response<DeleteTaskResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkDeleteResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkDeleteResponse(response: Response<DeleteTaskResponse>) {
        this.activity?.displayToast("Deleted Task!")
        response.body()?.run {

        }
    }

    private fun handleSomethingWentWrong() = this.activity?.displayToast("Something went wrong!")

    private fun checkList(notes: MutableList<BackendTask>) {
        if (notes.isEmpty()) {
            noData.visible()
        } else {
            noData.gone()
        }
    }

    private fun onTaskiesReceived(taskies: MutableList<BackendTask>) = adapter.setData(taskies)

}