package ainfinity.com.pronounce.application.activities

import ainfinity.com.pronounce.application.helpers.AppSettings
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import ainfinity.com.pronounce.application.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onStart() {
        super.onStart()
        val context = applicationContext
        AppSettings.instance.context = context
        AppSettings.instance.copyFileToInternalStorage(context,"settings.properties")
        AppSettings.instance.readSettings()

        if(AppSettings.instance.isFirstlogin!! == true || AppSettings.instance.isLoggedIn == false ){
            showLoginActivity()
        } else {
            showNavigationBottomBar()
        }
    }

    fun showLoginActivity(){
        print("Pronounce:" + "showLoginActivity")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showNavigationBottomBar(){
        print("Pronounce:" + "showNavigationBottomBar")
    }

}
