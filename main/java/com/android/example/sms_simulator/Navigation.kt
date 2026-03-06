package com.android.example.sms_simulator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.example.sms_simulator.Screens.InboxScreen
import com.android.example.sms_simulator.Screens.OtpScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  "inbox") {
        composable("inbox") {
            InboxScreen(
                hasMessage = true ,
                onClick = {
                    navController.navigate("otp")
                }
            )
        }
        composable("otp") {
                OtpScreen(navController = navController)
        }
    }
}