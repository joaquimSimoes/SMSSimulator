package com.android.example.sms_simulator.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(hasMessage: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Caixa de Entrada", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        if (hasMessage){
        Card(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("App1", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(4.dp))
                Text("Nova mensagem: Código de uso único recebido.", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }else{
        Text("Não tem nenhuma mensagem ainda.")
    }
    }
}