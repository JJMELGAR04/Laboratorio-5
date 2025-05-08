package com.falconteam.laboratorio3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.falconteam.laboratorio3.navigation.Sensor
import com.falconteam.laboratorio3.navigation.ToDoList

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate(ToDoList)}) {
            Text("Ir a lista")
        }
        Button(onClick = { navController.navigate(Sensor) }) {
            Text("Ver Sensores")
        }

    }
}
