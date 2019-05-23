package osc.ada.terezijaumiljanovic.bestpizzas.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.common.showFragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())
        setUpUi()

    }

    protected fun showFragment(fragment: Fragment) {
        showFragment(R.id.fragmentContainer, fragment)
    }

    abstract fun getLayoutResourceId(): Int
    abstract fun setUpUi()

}