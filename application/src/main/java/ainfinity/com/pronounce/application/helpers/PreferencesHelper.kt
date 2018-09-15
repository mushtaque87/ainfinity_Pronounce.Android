package ainfinity.com.pronounce.application.helpers

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper (context: Context) {

    companion object {

        private val USER_ID = "USER_ID"
        private val ACCESS_TOKEN = "ACCESS_TOKEN"
        private val REFRESH_TOKEN = "REFRESH_TOKEN"
        private val EXPIRES_IN = "EXPIRES_IN"


        private val userid : String? = null
        private val accesstoken : String? = null
        private val refreshtoken : String? = null
        private val expiredIn : String? = null
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var userid = preferences.getString(USER_ID, "")
        set(value) = preferences.edit().putString(USER_ID,value).apply()

    var accessToken = preferences.getString(ACCESS_TOKEN, "")
        set(value) = preferences.edit().putString(ACCESS_TOKEN,value).apply()


    var refreshtoken = preferences.getString(REFRESH_TOKEN, "")
        set(value) = preferences.edit().putString(REFRESH_TOKEN,value).apply()

    var expiredIn = preferences.getString(EXPIRES_IN, "")
        set(value) = preferences.edit().putString(EXPIRES_IN,value).apply()
}
