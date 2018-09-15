package ainfinity.com.pronounce.application.adapters

import ainfinity.com.pronounce.application.servicemanager.HTTPManager

class LoginViewModel {


    fun doLogin(username : String , password: String) {

        println("username $username password $password")
        HTTPManager.doLogin(username,password, {
            println("Login Success")
        },{
            println("Login Failed")
        })
    }

    fun signUp() {

    }

    fun forgetPassword() {

    }


}