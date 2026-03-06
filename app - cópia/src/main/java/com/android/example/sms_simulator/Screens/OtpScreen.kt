package com.android.example.sms_simulator.Screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(navController: NavController) {
    val context = LocalContext.current


    val sharedPrefs = remember { context.getSharedPreferences("otp_store", Context.MODE_PRIVATE) }
    var otpCode by remember { mutableStateOf(sharedPrefs.getString("last_otp", "Nenhum código recebido ainda.")) }
    var messageShow by remember { mutableStateOf("Nenhum código recebido ainda") }
    LaunchedEffect(Unit) {
        otpCode = sharedPrefs.getString("last_otp", "Nenhum código recebido ainda.")

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Código de uso Único ") },
                navigationIcon ={
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    } },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = Color.Black,
                )
            )
        }){ innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    item {
                        MessageBubble(
                            message = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                    color = Color.Black)){
                                append("O seu Código de uso único é: ")
                                withStyle(
                                    style = SpanStyle(
                                        textDecoration = TextDecoration.Underline,
                                        color = Color.Black
                                    )
                                ) {
                                    append(otpCode ?: "")
                                }
                                append("\n\nNão partilhe este código com ninguém.")
                                        }    },
                            from = " Aplicação de Autenticação"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
