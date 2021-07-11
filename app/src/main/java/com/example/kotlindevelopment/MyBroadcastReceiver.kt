package com.example.kotlindevelopment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals("com.tester.alarmmanager")){
            val b=intent.extras
           // Toast.makeText(context,b.getString("message"),Toast.LENGTH_LONG).show()
            val notifyMe= Notifications()
            notifyMe.Notify(context!!, b!!.getString("message")!!,10)
        }
        else if(intent.action.equals("android.intent.action.BOOT_COMPLETED")){

            val saveData= SaveData(context!!)
            saveData.setAlarm()
        }
    }

}
