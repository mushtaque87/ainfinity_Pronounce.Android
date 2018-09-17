package ainfinity.com.pronounce.application.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.time.format.DateTimeFormatter

fun Date.dateFromEpoc(epoc:Long) : Date {
    val date = Date(epoc)
    return  date

}

fun Date.toText(format : String = "EEE, d MMM, yyyy") : String{

    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
   return formatter.format(this)

}

