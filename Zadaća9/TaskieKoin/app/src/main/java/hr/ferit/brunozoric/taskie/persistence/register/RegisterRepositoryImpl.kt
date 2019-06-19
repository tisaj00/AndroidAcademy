package hr.ferit.brunozoric.taskie.persistence.register

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.networking.AuthService
import retrofit2.Call

class RegisterRepositoryImpl(private val authService: AuthService): RegisterRepository {
    override fun registerUser(body: UserDataRequest): Call<RegisterResponse> = authService.register(body)
}