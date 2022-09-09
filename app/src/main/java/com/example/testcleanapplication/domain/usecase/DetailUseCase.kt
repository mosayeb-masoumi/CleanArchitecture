package com.example.testcleanapplication.domain.usecase

import com.example.testcleanapplication.common.Resource
import com.example.testcleanapplication.data.remote.dto.todos_dto.toTodosModel
import com.example.testcleanapplication.domain.model.TodosModel
import com.example.testcleanapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(id: Int): Flow<Resource<TodosModel>> = flow {

        try {
            emit(Resource.Loading())
            val todoList = repository.getTodosList().map { it.toTodosModel() }
            val todoModel = todoList[id-1]
            emit(Resource.Success(todoModel))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occured", null))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "check your internet connection", null))
        }
    }
}