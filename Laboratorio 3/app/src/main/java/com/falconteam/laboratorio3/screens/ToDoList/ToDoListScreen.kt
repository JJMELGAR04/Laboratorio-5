package com.falconteam.laboratorio3.screens.ToDoList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.falconteam.laboratorio3.ViewModel.GeneralViewModel
import com.falconteam.laboratorio3.models.Card
import com.falconteam.laboratorio3.models.Task
import com.falconteam.laboratorio3.ui.components.Cards
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(navController: NavController, viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()

    val openDialog = remember { mutableStateOf(false) }
    val newCard = remember { mutableStateOf(Card(0, "", "")) }
    val openDateDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { openDialog.value = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
    ) { innerPadding ->
        if (openDialog.value) {
            Dialog(onDismissRequest = { openDialog.value = false }) {
                Card(modifier = Modifier.padding(16.dp)) {
                    Text("Agregar tarea", modifier = Modifier.padding(16.dp))
                    TextField(
                        value = newCard.value.title,
                        onValueChange = { newCard.value = newCard.value.copy(title = it) },
                        label = { Text("Title") },
                        modifier = Modifier.padding(16.dp)
                    )
                    TextField(
                        value = newCard.value.description,
                        onValueChange = { newCard.value = newCard.value.copy(description = it) },
                        label = { Text("Description") },
                        modifier = Modifier.padding(16.dp)
                    )
                    TextField(
                        value = newCard.value.endDate.toString(),
                        readOnly = true,
                        onValueChange = {},
                        label = { Text("Date") },
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(
                        onClick = { openDateDialog.value = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Select Date")
                    }

                    if (openDateDialog.value) {
                        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Date().time)
                        DatePickerDialog(
                            onDismissRequest = { openDateDialog.value = false },
                            confirmButton = {
                                Button(onClick = {
                                    openDateDialog.value = false
                                    newCard.value = newCard.value.copy(
                                        endDate = datePickerState.selectedDateMillis?.let { Date(it) } ?: Date()
                                    )
                                }) { Text("OK") }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    Button(
                        onClick = {
                            val task = Task(
                                id = newCard.value.pos,
                                title = newCard.value.title,
                                description = newCard.value.description,
                                endDate = newCard.value.endDate,
                                isCompleted = newCard.value.checked
                            )
                            viewModel.addTask(task)
                            openDialog.value = false
                            newCard.value = Card(0, "", "")
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Add Task")
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(tasks.value.size) { index ->
                val task = tasks.value[index]
                Cards(
                    pos = index,
                    max = tasks.value.size - 1,
                    title = task.title,
                    description = task.description,
                    endDate = task.endDate,
                    checked = task.isCompleted,
                    delete = { viewModel.deleteTask(index) },
                    check = { checked, _ -> viewModel.toggleCheck(index, checked) },
                    changePosition = { _, _ -> } // Optional
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
