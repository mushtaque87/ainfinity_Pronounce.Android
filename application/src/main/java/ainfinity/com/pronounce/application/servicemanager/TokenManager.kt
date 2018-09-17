package ainfinity.com.pronounce.application.servicemanager


import ainfinity.com.pronounce.application.datamodels.UserManagement.LoginResponseModel
import ainfinity.com.pronounce.application.helpers.AppSettings
import ainfinity.com.pronounce.application.helpers.PreferencesHelper


class TokenManager private constructor() {
    init {  }

    private object Holder { val INSTANCE = TokenManager() }

    companion object {
        val instance: TokenManager by lazy { Holder.INSTANCE }
    }
    var usedid:String? = null
    var accessToken: String? = null
    var refreshToken: String? = null


    fun storeNewToken(userDetails:LoginResponseModel) {
        this.usedid =  userDetails.uid
        this.accessToken = userDetails.access_token
        this.refreshToken = userDetails.refresh_token

        val preferencesHelper = PreferencesHelper(AppSettings.instance.context!!)
        preferencesHelper.userid = this.usedid
        preferencesHelper.accessToken =  this.accessToken
        preferencesHelper.refreshtoken = this.refreshToken




    }

}