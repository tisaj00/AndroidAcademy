package hr.ferit.brunozoric.taskie.persistence.login

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.networking.AuthService
import retrofit2.Call

class LoginRepositoryImpl(private val authService: AuthService):LoginRepository {
    override fun loginUser(body: UserDataRequest): Call<LoginResponse> = authService.login(body)
}