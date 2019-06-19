package hr.ferit.brunozoric.taskie.domain.tasks

import android.util.Log
import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.persistence.tasks.TasksRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class TasksUseCaseImpl(private val tasksRepository:TasksRepository):TasksUseCase{
    override fun execute(onSuccess: SuccessLambda<GetTasksResponse>, onFailure: ErrorLambda){
        tasksRepository.getTasks().enqueue(object : Callback<GetTasksResponse>{
            override fun onFailure(call: Call<GetTasksResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<GetTasksResponse>, response: Response<GetTasksResponse>) {
                Log.d("tag",response.body().toString())
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run {

                    onFailure(IllegalStateException("Something went wrong"))
                }
            }
        })
    }
}