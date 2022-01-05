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
                val output = setAlarm(hour!!,min!!)
                result.success(output) 
          }
        }
    }



    private fun setAlarm(hour:Int , min:Int )  {
        print("Testing setAlarm native___________________________$hour $min");
       
        MyAlarmManager.createAlarm(this,object: MyAlarmListener{
            override fun getDescription(): String {
                return "hello";

            }

            override fun getId(): Int {
                return 12345;
            }

            override fun getTitle(): String {
               return "test";
            }

            override fun listOfDays(): ArrayList<Int> {
                return arrayListOf(Days.MONDAY);
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