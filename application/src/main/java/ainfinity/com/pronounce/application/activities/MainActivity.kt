package ainfinity.com.pronounce.application.activities

import ainfinity.com.pronounce.application.helpers.AppSettings
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.helpers.Helper
import android.view.View
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    var mainActivityView : ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityView =  findViewById(R.id.mainActivity) as ViewGroup

    }

    override fun onStart() {
        super.onStart()
        val context = applicationContext
        AppSettings.instance.context = context
        AppSettings.instance.updateFileInInternalStorage(context,"settings.properties")
        AppSettings.instance.readSettings()



        Helper.readFile("",this)

    }

    override fun onResume() {
        super.onResume()

        if(AppSettings.instance.isFirstlogin!! == true || AppSettings.instance.isLoggedIn == false ){
            showLoginActivity()
        } else {
            showTabBarActivity()
        }
    }

    fun showLoginActivity(){

        Helper.printLogs("Pronounce:" + "showLoginActivity")
        val intent = Intent(this, LoginActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

    public fun showTabBarActivity(){
        Helper.printLogs("Pronounce:" + "showTabBarActivity")
        val intent = Intent(this, TabBarActivity::class.java)
        this.startActivity(intent)
       // val tabBar = TabBarActivity() as View
       // mainActivityView?.addView(tabBar)

    }

}
