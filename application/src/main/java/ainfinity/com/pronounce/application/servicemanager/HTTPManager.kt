package ainfinity.com.pronounce.application.servicemanager

import ainfinity.com.pronounce.application.datamodels.UserManagement.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import ainfinity.com.pronounce.application.helpers.Constants
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.nio.charset.Charset

class HTTPManager {

    companion object {

        fun generateAuthHeaders(): Map<String, String> {
            return mapOf("Authorization" to String.format("Bearer %s", TokenManager.instance.accessToken), "Refresh-Token" to TokenManager.instance.refreshToken.toString())
        }

        fun doLogin(username: String, passsword: String,
                    successCompletionHandler: (LoginResponseModel) -> Unit,
                    failureCompletionHandler: (HTTPError?) -> Unit) {

            val json = JSONObject()
            json.put("username", username)
            json.put("password", passsword)


            Fuel.post(Constants.login)
                    .header(mapOf("device-type" to "mobile", "Content-Type" to "application/json"))
                    .body(json.toString())
                    .responseString() {request , response , result ->
                        when (result) {
                            is Result.Success -> {

                                val data = result.get()
                                val gson = GsonBuilder().create()
                                val serverResponse = gson.fromJson(data, LoginResponseModel::class.java)
                                TokenManager.instance.storeNewToken(serverResponse)
                                successCompletionHandler(serverResponse)

                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                                val errorResponse = Gson().fromJson(response.data.toString(Charset.defaultCharset()),HTTPError::class.java)
                                failureCompletionHandler(errorResponse)
                            }
                        }
                    }
        }

        fun forgotPassword(email: String) {
            val json = JSONObject()
            json.put("email", email)


            Fuel.post(Constants.forgotpassword)
                    .header(mapOf("Content-Type" to "application/json"))
                    .body(json.toString())
                    .responseString() { request, response, result ->
                        when (result) {

                            is Result.Success -> {
                                val data = result.get()
                                val serverResponse = Gson().fromJson(data, ForgotPasswordModel::class.java)

                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                            }
                        }
                    }
        }



        fun getContentGroup(id: Int, uid: String, type: String,
                            successCompletionHandler: (ContentGroup) -> Unit,
                            failureCompletionHandler: (HTTPError) -> Unit) {

            var url: String?
            if (id == 0) {
                url = String.format(Constants.rootContent, uid, type)
            } else {
                url = String.format(Constants.contentGroup, uid, id)
            }

            Fuel.get(url)
                    .header(generateAuthHeaders())
                    .responseString() { request, response, result ->
                        when (result) {

                            is Result.Success -> {
                                val data = result.get()
                                val serverResponse = Gson().fromJson(data, ContentGroup::class.java)
                                successCompletionHandler(serverResponse)
                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                                val errorResponse = Gson().fromJson(response.data.toString(Charset.defaultCharset()),HTTPError::class.java)
                                failureCompletionHandler(errorResponse)
                            }
                        }

                    }


        }
    }
}




