package com.falconteam.laboratorio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.falconteam.laboratorio3.navigation.NavGraph
import com.falconteam.laboratorio3.ui.theme.Laboratorio3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio3Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio3Theme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}