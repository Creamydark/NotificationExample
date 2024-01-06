package com.creamydark.notificationexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "channel_id_0",
            "NotificationChannelExample",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}