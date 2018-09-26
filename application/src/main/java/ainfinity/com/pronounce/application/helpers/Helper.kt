package ainfinity.com.pronounce.application.helpers
import android.content.Context
import java.io.*



class Helper {

    companion object {
       public fun printLogs(value:String) {
            println("Pronounce: ${value}")
        }

        fun readFile(fileName : String ,  context: Context) : String {

            val returnString = StringBuilder()
            var fIn: InputStream? = null
            var isr: InputStreamReader? = null
            var input: BufferedReader? = null
            try {
                fIn = context.getResources().getAssets()
                        .open(fileName, Context.MODE_WORLD_READABLE)
                isr = InputStreamReader(fIn)
                input = BufferedReader(isr)
                while ((input.readLine()) != null) {
                    returnString.append(input.readLine())
                }
            } catch (e: Exception) {
                e.message
            } finally {
                try {
                    if (isr != null)
                        isr.close()
                    if (fIn != null)
                        fIn.close()
                    if (input != null)
                        input.close()
                } catch (e2: Exception) {
                    e2.message
                }

            }
            return returnString.toString()

        }



    }
}