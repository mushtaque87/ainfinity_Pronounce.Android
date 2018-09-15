package ainfinity.com.pronounce.application.datamodels.UserManagement

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class LoginResponseModel(
        val uid: String,
        val access_token: String,
        val expires_in: Int,
        val refresh_expires_in: Int,
        val refresh_token: String,
        val token_type: String,
        val session_state: String,
        val first_name: String,
        val last_name: String
)

class  Deserializer : ResponseDeserializable<LoginResponseModel> {
    override fun deserialize(content: String): LoginResponseModel?  = Gson().fromJson(content, LoginResponseModel::class.java)
}