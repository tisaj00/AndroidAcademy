package hr.ferit.brunozoric.taskie.networking

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthService {

    @POST(REGISTER_URL)
    fun register(@Body userData: UserDataRequest): Call<RegisterResponse>

    @POST(LOGIN_URL)
    fun login(@Body userData: UserDataRequest): Call<LoginResponse>

    @GET(NOTE_URL)
    fun getTasks(): Call<GetTasksResponse>

    @POST(NOTE_URL)
    fun save(@Body taskData: AddTaskRequest): Call<BackendTask>
}