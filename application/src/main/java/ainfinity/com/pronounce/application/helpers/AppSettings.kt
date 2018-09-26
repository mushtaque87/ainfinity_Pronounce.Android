package ainfinity.com.pronounce.application.helpers

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import java.io.*
import java.nio.file.Files.exists




class AppSettings private constructor() {

    init {
    }

    private object Holder {
        val INSTANCE = AppSettings()
    }

    companion object {
        val instance: AppSettings by lazy { Holder.INSTANCE }
    }

    var isLoggedIn:Boolean? = false
    var isFirstlogin: Boolean? = false
    var language: String? = null
    var graphType : Int? = 0
    var context:Context? = null

    fun updateFileInInternalStorage(context: Context, fileName: String) {
        // val context = super.getApplicationContext()

        val internalFilePath = getAppDataDirectory(context) + "/${fileName}"
        val file =  File(internalFilePath);
        if(file.exists()) {

        } else
        {
            copyFileToInternalStorage(context, internalFilePath)
        }





    }

    private fun getAppDataDirectory(context: Context = this.context!!): String {
    return context.getFilesDir().getPath().toString()
    }

    private fun copyFileToInternalStorage(context: Context, filePath: String) {
        val assetManager = context.assets

        try {
            val instream = assetManager.open(filePath)
            val out = FileOutputStream(filePath)
            val buffer = ByteArray(1024)
            var read = instream.read(buffer)
            while (read != -1) {
                out.write(buffer, 0, read)
                read = instream.read(buffer)
            }
        } catch (e: Exception) {
            e.message
        }

    }

    fun readSettings() {
        val properties = Properties()
        val propertiesFile = getAppDataDirectory(context!!) + "/settings.properties";

        val inputStream = FileInputStream(propertiesFile)
        properties.load(inputStream)

        properties.forEach{(k, v) ->
            println("key = $k, value = $v")
            when (k) {
                "isLoggedIn" -> this.isLoggedIn = v.toString().toBoolean()

                "isFirstlogin" ->  this.isFirstlogin =  v.toString().toBoolean()

                "language" ->  this.language = v.toString()

                "graphType"  ->  this.graphType = v.toString().toInt()

            }
        }

        println("----------------------------");
    }

    fun setValue(context: Context,fileName: String, key:String , value:Any) : Unit {

        val properties = Properties()
       /* when(key){

            "isLoggedIn" ->
                properties.put(key, value.toString().toBoolean())

            "isFirstlogin" ->
                properties.put(key, value.toString().toBoolean())
        }*/
        properties.put(key, value.toString())

        val propertiesFilePath = getAppDataDirectory(context) + "/${fileName}"

        val fileOutputStream = FileOutputStream(propertiesFilePath)
        properties.store(fileOutputStream, "Save to properties file")


    }

}


