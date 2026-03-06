package com.android.example.sms_simulator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class OtpReceiverManifest : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.android.example.sms_simulator.RECEIVE_CODE") {
            val code = intent.getStringExtra("otp_code")
            Log.d("OtpReceiverManifest", "Código de uso Único recebido: $code")

            code?.let {
                context?.let { safeContext ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        showCodeNotification(safeContext, it)
                    } else {
                        // Optional: show fallback Toast
                        Toast.makeText(safeContext, "Código de uso Único: $it", Toast.LENGTH_LONG).show()
                    }

                    safeContext.getSharedPreferences("otp_store", Context.MODE_PRIVATE)
                        .edit()
                        .putString("last_otp", it)
                        .apply()
                }
            }
        }
    }
}