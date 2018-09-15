package ainfinity.com.pronounce.application.activities

import android.os.Bundle
import android.app.Activity
import ainfinity.com.pronounce.application.R
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.View
import android.widget.Toast
import ainfinity.com.pronounce.application.servicemanager.HTTPManager
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text

class LoginActivity : Activity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener { onClick(btn_login) }
        btn_forgotpassword.setOnClickListener { onClick(btn_forgotpassword) }
    }


     fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                println("Login Clicked")
                if (!validate()) {
                    onLoginFailed()
                    return
                }
                // viewModel.doLogin(usernameTextEdit.text.toString(),passwordTextEdit.text.toString())
               HTTPManager.doLogin(emailEditText.text.toString(),passwordEditText.text.toString(), {
                    println("Login Success" + it.access_token)
                    val intent = Intent(this, TabBarActivity::class.java)
                    startActivity(intent)
                },{
                    println("Login Failed" + it?.description)
                    val intent = Intent(this, TabBarActivity::class.java)
                    startActivity(intent)
                })

            }

            R.id.btn_forgotpassword-> {
                //HTTPManager.forgotPassword("demo@email.com")
                println("ForgetPassword Clicked")
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
