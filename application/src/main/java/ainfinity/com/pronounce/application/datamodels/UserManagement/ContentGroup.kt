package ainfinity.com.pronounce.application.datamodels.UserManagement

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