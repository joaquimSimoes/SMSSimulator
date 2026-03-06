package com.android.example.sms_simulator

import android.content.IntentFilter
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController

@Composable
fun MessageApp() {
    val navController = rememberNavController()

    AppNavGraph(
        navController = navController
    )
}