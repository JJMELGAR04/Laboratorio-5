package com.falconteam.laboratorio3.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

import com.falconteam.laboratorio3.screens.HomeScreen
import com.falconteam.laboratorio3.screens.SensorScreen
import com.falconteam.laboratorio3.ViewModel.GeneralViewModel
import com.falconteam.laboratorio3.screens.ToDoList.ToDoListScreen

@Serializable
object Home
@Serializable
object ToDoList
@Serializable
object Sensor



@Composable
fun NavGraph(navController: NavHostController){
    val viewModel: GeneralViewModel = viewModel();
    NavHost(navController = navController, startDestination = ToDoList) {
        composable<Home> { HomeScreen( navController = navController ) }
        composable<ToDoList> { ToDoListScreen( navController = navController, viewModel = viewModel) }
        composable<Sensor> { SensorScreen(navController=navController) }
    }
}