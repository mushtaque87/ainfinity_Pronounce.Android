package ainfinity.com.pronounce.application.activities

import android.os.Bundle
import android.app.Activity
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.helpers.AppSettings
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.View
import android.widget.Toast
import ainfinity.com.pronounce.application.servicemanager.ServiceManager
import android.graphics.Color
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text
import android.graphics.Color.parseColor

import android.graphics.Color.DKGRAY
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower





class LoginActivity : Activity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_tab_bar)


        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener { onClick(btn_login) }
        btn_forgotpassword.setOnClickListener { onClick(btn_forgotpassword) }
    }


     fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                println("Login Clicked")

               // AppSettings.instance.isFirstlogin = false
               // AppSettings.instance.isLoggedIn == true



//                val mainIntent = Intent(applicationContext, MainActivity::class.java)
//                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
//                startActivity(mainIntent)
//                setResult(RESULT_OK,mainIntent)
//                finish()






                if (!validate()) {
                    onLoginFailed()
                    return
                }

                val progressDialog = ACProgressFlower.Builder(this)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .themeColor(Color.BLACK)
                        .bgColor(Color.WHITE)
                        .text("Logging in. Please wait")
                        .textSize(40)
                        .textColor(Color.BLACK)
                        .textAlpha(1f)
                        .fadeColor(Color.GRAY)
                        .isTextExpandWidth(true).build()
                progressDialog.show()

                // viewModel.doLogin(usernameTextEdit.text.toString(),passwordTextEdit.text.toString())
                ServiceManager.doLogin(emailEditText.text.toString(),passwordEditText.text.toString(), {

                    println("Login Success" + it.access_token)
                    progressDialog.dismiss()

                    AppSettings.instance.setValue(applicationContext,"settings.properties","isLoggedIn",true)
                    AppSettings.instance.setValue(applicationContext,"settings.properties","isFirstlogin",false)

                    val intent = Intent(this, TabBarActivity::class.java)
                    startActivity(intent)

                },{
                    println("Login Failed" + it?.description)
                    progressDialog.dismiss()


                })

            }

            R.id.btn_forgotpassword-> {
                //HTTPManager.forgotPassword("demo@email.com")
                println("ForgetPassword Clicked")
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            else -> { //your code
                println("Button Unidentified")

            }
        }
    }

    fun login() {
        Log.d(TAG, "Login")

        if (!validate()) {
            onLoginFailed()
            return
        }

        btn_login.setEnabled(false)

        val progressDialog = ProgressDialog(this@LoginActivity,
                R.style.AppTheme)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()

        val email = emailEditText.text.toString()
        val password = emailEditText.text.toString()

        // TODO: Implement your own authentication logic here.

        android.os.Handler().postDelayed(
                {
                    // On complete call either onLoginSuccess or onLoginFailed
                    onLoginSuccess()
                    // onLoginFailed();
                    progressDialog.dismiss()
                }, 3000)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       /* if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == Activity.RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish()
            }
        }*/
    }

    override fun onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true)
    }

    fun onLoginSuccess() {
        btn_login.setEnabled(true)
        finish()
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()

        btn_login.setEnabled(true)
    }

    fun validate(): Boolean {
        var valid = true

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("enter a valid email address")
            valid = false
        } else {
            emailEditText.setError(null)
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            passwordEditText.setError("between 4 and 10 alphanumeric characters")
            valid = false
        } else {
            passwordEditText.setError(null)
        }

        return valid
    }

}
