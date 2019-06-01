package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.model.request.GetTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.model.response.UpdateResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*
import kotlinx.android.synthetic.main.fragment_task_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskDetailsFragment : BaseFragment() {

    private val interactor = BackendFactory.getTaskieInteractor()
    private var taskID : String = "NO_TASK"

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
    }

    private fun tryDisplayTask(id: String) {
        try {
            interactor.getTask(GetTaskRequest(id),getTaskCallback())
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun displayTask(task: BackendTask) {
        detailsTaskTitle.text = task.title
        detailsTaskDescription.text = task.content


        when(task.taskPriority){
            1->{detailsPriorityView.setBackgroundResource(R.color.colorLow)}
            2->{detailsPriorityView.setBackgroundResource(R.color.colorMedium)}
            3->{detailsPriorityView.setBackgroundResource(R.color.colorHigh)}
            else -> {Log.d("tag","no task priority")}
        }
    }

    private fun tryEditTask(task: BackendTask) {
        val title = editTaskTitleInput.text.toString()
        val description = editTaskDescriptionInput.text.toString()
        val priorityNew = editPrioritySelector.selectedItem

        when (priorityNew) {
            "Low" -> {interactor.editNote(UpdateTaskRequest(id = task.id,title = title,content = description,taskPriority = 1), updateTaskCallback())}
            "Medium" -> {interactor.editNote(UpdateTaskRequest(id = task.id,title = title,content = description,taskPriority = 2), updateTaskCallback())}
            "High" -> {interactor.editNote(UpdateTaskRequest(id = task.id,title = title,content = description,taskPriority = 3), updateTaskCallback())}
        }
    }

    private fun getTaskCallback(): Callback<BackendTask> = object : Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>?, t: Throwable?) {
            //TODO
        }
        override fun onResponse(call: Call<BackendTask>?, response: Response<BackendTask>) {

            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkGetTaskResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkGetTaskResponse(response: Response<BackendTask>) {

        response.body()?.run {
            displayTask(this)
            editSaveTaskAction.setOnClickListener {
                tryEditTask(this)
            }
        }
    }

    private fun updateTaskCallback(): Callback<UpdateResponse> = object : Callback<UpdateResponse> {
        override fun onFailure(call: Call<UpdateResponse>?, t: Throwable?) {
            Log.d("tag","didn't update task")
        }
        override fun onResponse(call: Call<UpdateResponse>?, response: Response<UpdateResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkUpdateTaskResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkUpdateTaskResponse(response: Response<UpdateResponse>) {

        this.activity?.displayToast("Updated task!")

        response.body()?.run {

        }
    }

    private fun handleSomethingWentWrong() = this.activity?.displayToast("Something went wrong with getting task by ID!")

    companion object {
        const val NO_TASK = ""
        const val NO_TASK_STRING = "no task"

        fun newInstance(taskId: String): TaskDetailsFragment {
            val bundle = Bundle().apply { putString(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}

