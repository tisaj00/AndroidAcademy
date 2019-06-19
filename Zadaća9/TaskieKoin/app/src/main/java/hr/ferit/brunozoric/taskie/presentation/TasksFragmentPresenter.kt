package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.tasks.TasksUseCase
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.ui.tasklist.fragment.TasksFragmentContract


class TasksFragmentPresenter(private val tasksUseCase: TasksUseCase): TasksFragmentContract.Presenter {

    private lateinit var view: TasksFragmentContract.View

    override fun setView(view: TasksFragmentContract.View) {
        this.view = view
    }

    override fun onGetAllTasks() {
        tasksUseCase.execute(::handleOkResponse,::handleSomethingWentWrong)
    }

    private fun handleOkResponse(response: GetTasksResponse) {
        response?.notes?.run {
            view.onTaskListRecieved(this)
        }
    }

    private fun handleSomethingWentWrong(error:Throwable) = view.onGetTasksFailed(error)
}