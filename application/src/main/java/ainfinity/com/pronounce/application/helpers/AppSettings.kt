package ainfinity.com.pronounce.application.helpers

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*


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

    fun copyFileToInternalStorage(context: Context, fileName: String) {
        // val context = super.getApplicationContext()
        copyFile(context, fileName)

    }

    private fun getAppDataDirectory(context: Context = this.context!!): String {
    return context.getFilesDir().getPath().toString()
    }

    private fun copyFile(context: Context, fileName: String) {
        val assetManager = context.assets
        val internalPath = getAppDataDirectory(context) + "/${fileName}"
        try {
            val instream = assetManager.open(fileName)
            val out = FileOutputStream(internalPath)
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


    fun setSettings(key: String) {

    }


        println("----------------------------");
    }
}


