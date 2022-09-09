package com.example.testcleanapplication.data.repository

import com.example.testcleanapplication.data.remote.ApiService
import com.example.testcleanapplication.data.remote.dto.todos_dto.TodosModelDto
import com.example.testcleanapplication.domain.repository.UserRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {

    override suspend fun getTodosList(): List<TodosModelDto> {
        return apiService.getTodosList()
    }

    override suspend fun getDetailItem(id: Int?): TodosModelDto {
        return apiService.getDetailItem(id)
    }

}