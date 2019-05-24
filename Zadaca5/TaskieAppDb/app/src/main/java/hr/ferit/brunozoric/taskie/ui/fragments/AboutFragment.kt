package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.adapters.AboutFragmentAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment:BaseFragment() {

    companion object {
        fun newInstance():Fragment{
            return AboutFragment()
        }
    }

    override fun getLayoutResourceId(): Int=R.layout.fragment_about
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = AboutFragmentAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }


}