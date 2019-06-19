package hr.ferit.brunozoric.taskie.persistence.addTask

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.networking.AuthService
import retrofit2.Call

class AddTaskRepositoryImpl (private val authService: AuthService):AddTaskRepository{
    override fun addTask(task: AddTaskRequest): Call<BackendTask> = authService.save(task)
}