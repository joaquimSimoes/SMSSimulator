package com.android.example.sms_simulator

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.O)
fun showCodeNotification(context: Context, otpCode: String){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permission = "android.permission.POST_NOTIFICATIONS"
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            Log.w("Notification", "Permission to post notifications not granted.")
            return
        }
    }

    val channelID = "otp_channel_id"
    val channelName = "Notificações de código de uso único"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val importante = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelID, channelName, importante).apply{
            description = "Canal para notificações de código de uso único"
        }
        val notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    val intent = Intent(context, MainActivity::class.java).apply{
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        putExtra("otp_code", otpCode)
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, channelID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Código de uso Único Recebido")
        .setContentText("O seu código é: $otpCode")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)){
        notify(1001,builder.build())
    }

}