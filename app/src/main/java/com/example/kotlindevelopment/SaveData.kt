package com.example.kotlindevelopment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData(context: Context) {

    var context:Context?= context
    var sharedRef:SharedPreferences?=null

    init {
        sharedRef=context.getSharedPreferences("myref",Context.MODE_PRIVATE)
    }

    fun SaveData(hour:Int,minute:Int){
       val editor=sharedRef!!.edit()
        editor.putInt("hour",hour)
        editor.putInt("minute",minute)
        editor.apply()
    }

    fun  getHour():Int{
        return sharedRef!!.getInt("hour",0)
    }
    fun  getMinute():Int{
        return sharedRef!!.getInt("minute",0)
    }

    fun setAlarm(){
        val hour:Int=getHour()
         val minute:Int=getMinute()
        val calender=Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY,hour)
        calender.set(Calendar.MINUTE,minute)
        calender.set(Calendar.SECOND,0)

        val am= context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent=Intent(context, MyBroadcastReceiver::class.java)
        intent.putExtra("message"," alarm time")
        intent.action="com.tester.alarmmanager"
        val pi=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        am.setRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis,
                AlarmManager.INTERVAL_DAY,pi)

    }
}
