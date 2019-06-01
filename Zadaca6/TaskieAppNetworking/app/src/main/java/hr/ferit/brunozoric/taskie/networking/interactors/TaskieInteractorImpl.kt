package hr.ferit.brunozoric.taskie.networking.interactors

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.*
import hr.ferit.brunozoric.taskie.model.response.*
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Callback

class TaskieInteractorImpl(private val apiService: TaskieApiService) : TaskieInteractor {


    override fun deleteNote(request: DeleteTaskRequest, deleteCallback: Callback<DeleteTaskResponse>) {
        apiService.deleteNote(request.taskId).enqueue(deleteCallback)
    }


    override fun getTask(request: GetTaskRequest, taskieResponseCallback: Callback<BackendTask>) {
        apiService.getTask(request.taskId).enqueue(taskieResponseCallback)
    }

    override fun editNote(request: UpdateTaskRequest, updateTaskCallBack: Callback<UpdateResponse>) {
        apiService.editNote(request).enqueue(updateTaskCallBack)
    }

    override fun getTasks(taskieResponseCallback: Callback<GetTasksResponse>) {
        apiService.getTasks().enqueue(taskieResponseCallback)
    }

    override fun register(request: UserDataRequest, registerCallback: Callback<RegisterResponse>) {
        apiService.register(request).enqueue(registerCallback)
    }

    override fun login(request: UserDataRequest, loginCallback: Callback<LoginResponse>) {
        apiService.login(request).enqueue(loginCallback)
    }

    override fun save(request: AddTaskRequest, saveCallback: Callback<BackendTask>) {
        apiService.save(request).enqueue(saveCallback)
    }
}