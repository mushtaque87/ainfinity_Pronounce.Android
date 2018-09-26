package ainfinity.com.pronounce.application.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import ainfinity.com.pronounce.application.fragments.AssignmentFragment
import ainfinity.com.pronounce.application.fragments.ContentFragment
import android.support.v4.app.Fragment
import ainfinity.com.pronounce.application.fragments.SettingsFragment
import android.content.Intent
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.ContentType
import ainfinity.com.pronounce.application.helpers.AppSettings
import ainfinity.com.pronounce.application.helpers.Helper
import android.app.PendingIntent.getActivity
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import kotlin.collections.ArrayList
import android.support.v4.app.FragmentManager
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP





class TabBarActivity : AppCompatActivity() {

//   var mainNav : BottomNavigationView
//    var mainFrame : FrameLayout

    lateinit var toolbar : ActionBar
     var contentFragment = ContentFragment()
     var assignmentFragment = ContentFragment()
     var settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_bar)

/*
        val bottomNavigation: BottomNavigationView = findViewById(R.id.main_nav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {

            bottomNavigation.setSelectedItemId(R.id.nav_contents); // change to whichever id should be default
        }
     */

        val viewPager = findViewById(R.id.pager) as ViewPager
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Add Fragments to adapter one by one
        adapter.addFragment(ContentFragment(), "Content")
        adapter.addFragment(AssignmentFragment(), "Assignment")
        adapter.addFragment(SettingsFragment(), "Settings")
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3
        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)


        }




    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_contents -> {
                println("Contents Tab Clicked")

                contentFragment.contentAdapter.contentType = ContentType.CONTENT
                openFragment(contentFragment, R.id.nav_contents)

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_assignment -> {
                println("Assignments Tab Clicked")
                assignmentFragment.contentAdapter.contentType = ContentType.ASSIGNMENT
                openFragment(assignmentFragment, R.id.nav_assignment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                println("Settings Tab Clicked")

                openFragment(settingsFragment, R.id.nav_settings)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment , id : Int) {
        //val targetFragment = supportFragmentManager.findFragmentByTag(fragment.tag)
        val transaction = supportFragmentManager.beginTransaction()
        /*if (supportFragmentManager.findFragmentById(id) == null) {
            transaction.add

        } else {

            transaction.replace(R.id.container, fragment)
        }*/

        //transaction.add(R.id.container,fragment)
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLoginActivity() {
        Helper.printLogs("Call Main activity")
        AppSettings.instance.setValue(applicationContext,"settings.properties","isLoggedIn",false)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)

    }


}


// Adapter for the viewpager using FragmentPagerAdapter
internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }
}
