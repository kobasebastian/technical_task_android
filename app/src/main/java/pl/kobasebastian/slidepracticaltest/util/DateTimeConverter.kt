package pl.kobasebastian.slidepracticaltest.util

import java.text.SimpleDateFormat

object DateTimeConverter {
    fun getStringTimeFrom(date: String) : String{
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").parse(date)
        val interval = System.currentTimeMillis() - date.time
        val minsValue = interval/1000/60
        val stringValue = "$minsValue mins ago"
        return stringValue
    }
}