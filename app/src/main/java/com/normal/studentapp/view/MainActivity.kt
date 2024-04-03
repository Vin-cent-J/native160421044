package com.normal.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.normal.studentapp.R
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.normal.studentapp.databinding.ActivityMainBinding
import com.normal.studentapp.util.createNotifChannel

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    init {
        instance = this
    }
    companion object {
        private var instance:MainActivity ?= null

        fun showNotif(title: String, content: String, icon: Int){
            val channelID = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelID).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)

                return
            }

            manager.notify(1001, builder.build())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        createNotifChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, "App notification channel.")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
                    createNotifChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                        "Notification channel for creating new student")
                } else {
                    Log.d("permission", "not granted")
                }
                return
            }
        }

    }
}