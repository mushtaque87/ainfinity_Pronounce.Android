package ainfinity.com.pronounce.application.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.servicemanager.ServiceManager
import android.content.Intent
import android.view.View

import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.content_forgot_password.*
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import android.graphics.Color

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        resetButton.setOnClickListener { resetPassword() }
    }

    fun resetPassword() {
        val progressDialog = ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Resetting Password. Please Check email.")
                .fadeColor(Color.DKGRAY)
                .isTextExpandWidth(true).build()
        progressDialog.show()
        ServiceManager.forgotPassword(emailEditText.text.toString(), {
            println("Password Reset Successful" + it.success)
            progressDialog.dismiss()
        },{

            progressDialog.dismiss()
            println("Password Reset Failed" + it?.description)

        })
    }

}
