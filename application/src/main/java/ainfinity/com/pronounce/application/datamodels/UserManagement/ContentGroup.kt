package ainfinity.com.pronounce.application.datamodels.UserManagement

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer



data class ContentGroup(
        val id: Int,
        val parent_id: Int,
        val root_id: Int,
        val name: String,
        val created_by: String,
        val creation_date: Long,
        val has_units: Boolean,
        val status: String,
        val description: String
)

//private class  Deserializer : ResponseDeserializable<ContentGroup> {
//    override fun deserialize(content: String): ContentGroup?  = Gson().fromJson(content, ContentGroup::class.java)
//}
//
//private class DateTimeDeserializer : JsonDeserializer<ContentGroup> {
//    @Throws(JsonParseException::class)
//    fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ContentGroup {
//        return ContentGroup(json.)
//    }
//}