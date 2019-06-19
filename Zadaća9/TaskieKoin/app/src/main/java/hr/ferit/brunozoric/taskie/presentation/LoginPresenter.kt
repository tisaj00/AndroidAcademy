package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.login.LoginUseCase
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import hr.ferit.brunozoric.taskie.ui.login.LoginContract

class LoginPresenter(private val loginUseCase: LoginUseCase):LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    val prefsShared= provideSharedPrefs()

    override fun onUserLogin(user: UserDataRequest) {
        loginUseCase.execute(user,::handleOkResponse,::handleSomethingWentWrong)
    }

    private fun handleOkResponse(loginResponse: LoginResponse?) {
        loginResponse?.token?.let {
            prefsShared.storeUserToken(loginResponse.token)
        }
        view.onLoginSuccesfull()
    }

    private fun handleSomethingWentWrong(error:Throwable) {
        view.onLoginFailed()
    }
}