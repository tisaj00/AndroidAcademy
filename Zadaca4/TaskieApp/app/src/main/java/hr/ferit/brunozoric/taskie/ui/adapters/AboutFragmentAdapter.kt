package hr.ferit.brunozoric.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.AppAbout
import hr.ferit.brunozoric.taskie.ui.fragments.AutorAbout

class AboutFragmentAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {

    val fragments:Array<Fragment> = arrayOf(
        AppAbout.newInstance(),
        AutorAbout.newInstance()
    )

    val title = arrayOf("About App","About Autor")


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}