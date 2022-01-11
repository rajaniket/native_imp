package com.example.native_impl

import android.os.Bundle
import android.os.PersistableBundle
import web.o.alarm.Days
import web.o.alarm.MyAlarmListener
import web.o.alarm.MyAlarmManager
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.google.android.material.R
import android.app.Activity  
import android.content.Intent  
import android.net.Uri  
import io.flutter.plugin.common.MethodCall   
import io.flutter.plugin.common.MethodChannel.MethodCallHandler  
import io.flutter.plugin.common.MethodChannel.Result  
import io.flutter.plugins.GeneratedPluginRegistrant  




class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.relife/alarm"

   override 
    fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
         print("Testing setAlarm configure engine___________________________");
        
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
          call, result -> 
          if(call.method == "setAlarm")
          {
               val arguments = call.arguments()as Map<String,Int>
                val hour = arguments["hour"]
               val min = arguments["min"]
               setAlarm(hour!!,min!!)
               
          }
        }
    }



    private fun setAlarm(hour:Int , min:Int )  {
        print("Testing setAlarm native___________________________$hour $min");
       
        MyAlarmManager.createAlarm(this,object: MyAlarmListener{
            override fun getAuthToken(): String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MWNhZDliZDQ5MDlmOTAwMTE4YjQyODMiLCJpYXQiOjE2NDE1NjgwNTUsImV4cCI6MTY0MTY1NDQ1NX0.dbxul59YYwlC16ZRtupNwueV1SSEY_R8gq1_C_kRaVg"
            override fun getHabitId(): String = "61c9ad33e5d53c0011dab03e"
            
            override fun getPlan(): String = "after i have dinner ⏳\ni’ll read for 15 mins 😇\nso that i can watch netflix 🙈"   // 

            override fun getHabitPlan(): String = "it’s time to read!"   //

            override fun getDescription(): String {  // notification
                return "continue your seven days streak";

            }

            override fun getId(): Int {
                return 12345;
            }

            override fun getTitle(): String {  // notification
               return "it's time to read";
            }

            override fun listOfDays(): ArrayList<Int> { 
                return arrayListOf(Days.MONDAY,);
            }

            override fun listOfTimesInSec(): ArrayList<Long> {
                return arrayListOf((((hour * 60) + min) * 60).toLong());
            }

            override fun startDate(): String? {
                return null;
            }
        })
     
    }
}