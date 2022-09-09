package com.example.testcleanapplication.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcleanapplication.common.Resource
import com.example.testcleanapplication.domain.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: GetListUseCase) : ViewModel() {

    // livedata
    private val _response = MutableLiveData<TodosState>()
    val todosLivedata: MutableLiveData<TodosState> get() = _response


    // stateFlow
    private val _stateFlow = MutableStateFlow<TodosState>(TodosState())
    val todosStateFlow = _stateFlow.asStateFlow()





    // liveData
    fun getTodoListByLiveData() {
        useCase().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _response.value = TodosState(isLoading = true)
                }
                is Resource.Success -> {
                    _response.value = TodosState(data = result.data!!)
                }
                is Resource.Error -> {
                    _response.value = TodosState(error = "An unexpected error occured" )
                }

            }


        }.launchIn(viewModelScope)
    }



    //  flow
    fun getTodoListByFlow() {
        useCase().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _stateFlow.value = TodosState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateFlow.value = TodosState(data = result.data!!)
                }
                is Resource.Error -> {
                    _stateFlow.value = TodosState(error = "An unexpected error occured" )
                }

            }


        }.launchIn(viewModelScope)
    }




}