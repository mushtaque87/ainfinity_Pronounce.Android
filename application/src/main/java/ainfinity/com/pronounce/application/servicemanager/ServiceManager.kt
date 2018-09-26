package ainfinity.com.pronounce.application.servicemanager

import ainfinity.com.pronounce.application.datamodels.UserManagement.*
import ainfinity.com.pronounce.application.extensions.dateFromEpoc
import ainfinity.com.pronounce.application.extensions.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import ainfinity.com.pronounce.application.helpers.Constants
import ainfinity.com.pronounce.application.helpers.Helper
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.nio.charset.Charset
import com.google.gson.reflect.TypeToken
import java.util.*


class ServiceManager {

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
                                Helper.printLogs(data)
                                val gson = GsonBuilder().create()
                                val serverResponse = gson.fromJson(data, LoginResponseModel::class.java)
                                TokenManager.instance.storeNewToken(serverResponse)
                                successCompletionHandler(serverResponse)

                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                                Helper.printLogs(ex.localizedMessage)
                                val errorResponse = Gson().fromJson(response.data.toString(Charset.defaultCharset()),HTTPError::class.java)
                                if(errorResponse != null) {
                                    failureCompletionHandler(errorResponse)
                                } else {
                                    val httperror = HTTPError(description = "Server error. Please try again.", error_code = "500")
                                    failureCompletionHandler(httperror)
                                }
                            }
                        }
                    }
        }

        fun forgotPassword(email: String,
                           successCompletionHandler: (ForgotPasswordModel) -> Unit,
                           failureCompletionHandler: (HTTPError?) -> Unit) {
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
                                successCompletionHandler(serverResponse)
                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                                val errorResponse = Gson().fromJson(response.data.toString(Charset.defaultCharset()),HTTPError::class.java)
                                if(errorResponse != null) {
                                    failureCompletionHandler(errorResponse)
                                } else {
                                    val httperror = HTTPError(description = "Server error. Please try again.", error_code = "500")
                                    failureCompletionHandler(httperror)
                                }
                            }
                        }
                    }
        }



        fun getContentGroup(id: Int, uid: String, type: String,
                            successCompletionHandler: (Array<ContentGroup>) -> Unit,
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
//
                                //val serverResponse = Gson().fromJson<ContentGroup>(data,collectionType)
                                val serverResponse = Gson().fromJson(data, Array<ContentGroup>::class.java)
                               // val date = Date().dateFromEpoc(serverResponse[0].creation_date)
                               // val datetime = date.toString()
                                print("Response" + serverResponse)
                                successCompletionHandler(serverResponse)
                            }
                            is Result.Failure -> {
                                val ex = result.getException()
                                val errorResponse = Gson().fromJson(response.data.toString(Charset.defaultCharset()),HTTPError::class.java)
                                if(errorResponse != null) {
                                    failureCompletionHandler(errorResponse)
                                } else {
                                    val httperror = HTTPError(description = "Server error. Please try again.", error_code = "500")
                                    failureCompletionHandler(httperror)
                                }
                            }
                        }

                    }


        }
    }
}




