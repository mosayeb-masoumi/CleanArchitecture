package com.example.testcleanapplication.presentation.list

import com.example.testcleanapplication.domain.model.TodosModel

data class TodosState(
    val isLoading: Boolean = false,
    val data: List<TodosModel> = emptyList(),
//    val data: WeatherModel? = null,
    val error : String = ""
)
