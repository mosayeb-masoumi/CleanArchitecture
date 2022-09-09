package com.example.testcleanapplication.data.remote

import com.example.testcleanapplication.data.remote.dto.todos_dto.TodosModelDto
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("todos/")
    suspend fun getTodosList() : List<TodosModelDto>

    @GET("todos/{id}")
    suspend fun getDetailItem(@Query(value = "id") id: Int?) : TodosModelDto

    @GET("/fake/api")
    fun refreshToken(): Call<JsonObject?>?

}