package ainfinity.com.pronounce.application.helpers


class Constants {
    companion object {
        //val baseurl = "http://192.168.71.10:10010/api/"

        val baseurl = "https://api.ainfinity.ai:443/api/"
        val userService = "aiuam/v1/"

        val login = baseurl + userService + "users/login"
        val forgotpassword = baseurl + userService + "users/forget-password"

        val contentService = "content-service/v1/"
        val rootContent  = baseurl + contentService + "students/%s/contents?is_assignment=%s"
        val contentGroup  = baseurl + contentService + "students/%s/contents/%d"
    }
}