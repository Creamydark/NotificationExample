package com.creamydark.notificationexample

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.creamydark.notificationexample.ui.theme.NotificationExampleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun showNotification(text:String = ""){
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(
                applicationContext,
                "channel_id_0"
            )
                .setContentTitle("Hi This is sample notification.")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .build()
            notificationManager.notify(0,notification)
        }
        setContent {
            NotificationExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var someNotificationText by remember {
                        mutableStateOf("")
                    }

                    val permission = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

                    if (permission.status.isGranted){
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)){
                            Text(modifier = Modifier.align(Alignment.TopCenter), text = "Notification Granted")
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(value = someNotificationText, onValueChange ={someNotificationText = it} )
                                Button(onClick = {
                                    showNotification(
                                        text = someNotificationText
                                    )
                                }, modifier = Modifier) {
                                    Text(text = "Show Notification")
                                }
                            }
                        }
                    }else{
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)){
                            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                                permission.launchPermissionRequest()
                            }) {
                                Text(text = "Request Permission")
                            }
                        }
                    }
                }
            }
        }
    }
}


