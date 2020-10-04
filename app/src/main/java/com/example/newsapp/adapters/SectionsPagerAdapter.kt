package com.example.newsapp.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapp.R
import com.example.newsapp.fragments.EverythingFragment
import com.example.newsapp.fragments.TopHeadlinesFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_top_headlines_text,
    R.string.tab_everything_text
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopHeadlinesFragment()
            1 -> EverythingFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int) = context.resources.getString(TAB_TITLES[position])

    override fun getCount() = 2
}