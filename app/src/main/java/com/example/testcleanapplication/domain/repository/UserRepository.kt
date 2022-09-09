package com.example.testcleanapplication.domain.repository

import com.example.testcleanapplication.data.remote.dto.todos_dto.TodosModelDto
import retrofit2.http.Query
import javax.inject.Inject

interface UserRepository {

    suspend fun getTodosList() : List<TodosModelDto>
    suspend fun getDetailItem(@Query(value = "id") id: Int?) : TodosModelDto
}