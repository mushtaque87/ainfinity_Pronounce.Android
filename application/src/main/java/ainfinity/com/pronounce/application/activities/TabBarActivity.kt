package ainfinity.com.pronounce.application.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import ainfinity.com.pronounce.application.fragments.AssignmentFragment
import ainfinity.com.pronounce.application.fragments.ContentFragment
import android.support.v4.app.Fragment
import ainfinity.com.pronounce.application.fragments.SettingsFragment

import ainfinity.com.pronounce.application.R

class TabBarActivity : AppCompatActivity() {

//   var mainNav : BottomNavigationView
//    var mainFrame : FrameLayout

    lateinit var toolbar : ActionBar
     var contentFragment = ContentFragment()
     var assignmentFragment = AssignmentFragment()
     var settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_bar)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.main_nav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.nav_contents); // change to whichever id should be default
        }
        }




    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_contents -> {
                println("Contents Tab Clicked")


                openFragment(contentFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_assignment -> {
                println("Assignments Tab Clicked")

                openFragment(assignmentFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                println("Settings Tab Clicked")

                openFragment(settingsFragment)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
