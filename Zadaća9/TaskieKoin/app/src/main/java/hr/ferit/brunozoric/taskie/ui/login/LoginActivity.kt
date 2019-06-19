package hr.ferit.brunozoric.taskie.ui.login

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.ui.tasklist.MainActivity
import hr.ferit.brunozoric.taskie.ui.register.RegisterActivity
import hr.ferit.brunozoric.taskie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter by inject<LoginContract.Presenter>()

    override fun getLayoutResourceId(): Int = R.layout.activity_login

    override fun onResume() {
        super.onResume()
    }

    override fun setUpUi() {
        presenter.setView(this)

        login.onClick { signInClicked() }
        goToLogin.onClick { goToRegistrationClicked() }
    }

    private fun signInClicked() {
        presenter.onUserLogin(UserDataRequest(email.text.toString(), password.text.toString()))
    }


    private fun goToRegistrationClicked() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onLoginSuccesfull() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailed() {
        displayToast("Fail")
    }
}