package com.example.testcleanapplication.presentation.detail

import com.example.testcleanapplication.domain.model.TodosModel

data class DetailState(
    val isLoading: Boolean = false,
//    val data: List<TodosModel> = emptyList(),
    val data: TodosModel? = null,
    val error : String = ""
)