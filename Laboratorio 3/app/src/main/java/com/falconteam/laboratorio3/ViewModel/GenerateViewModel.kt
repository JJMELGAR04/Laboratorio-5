package com.falconteam.laboratorio3.ViewModel

import androidx.lifecycle.ViewModel
import com.falconteam.laboratorio3.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value.toMutableList().apply { add(task) }
    }

    fun toggleCheck(index: Int, checked: Boolean) {
        val current = _tasks.value.toMutableList()
        val task = current[index]
        current[index] = task.copy(isCompleted = checked)
        _tasks.value = current
    }

    fun deleteTask(index: Int) {
        _tasks.value = _tasks.value.toMutableList().apply { removeAt(index) }
    }
}