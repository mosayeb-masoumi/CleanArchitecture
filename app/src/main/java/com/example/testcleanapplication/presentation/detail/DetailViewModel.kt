package com.example.testcleanapplication.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcleanapplication.common.Resource
import com.example.testcleanapplication.domain.usecase.DetailUseCase
import com.example.testcleanapplication.presentation.list.TodosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase:DetailUseCase):ViewModel(){

    // liveData
    private val _responseLiveData = MutableLiveData<DetailState>()
    val detailLiveData : MutableLiveData<DetailState> get() = _responseLiveData

    // stateFlow
    private val _responseStateFlow = MutableStateFlow<DetailState>(DetailState())
    val detailStateFlow = _responseStateFlow.asStateFlow()




    // liveData
    fun getDetailByLiveData(id: Int) {
        useCase(id).onEach { result ->

            when (result) {
                is Resource.Loading -> {
                    _responseLiveData.value = DetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _responseLiveData.value = DetailState(data = result.data!!)
                }
                is Resource.Error -> {
                    _responseLiveData.value = DetailState(error = "An unexpected error occured" )
                }
            }

        }.launchIn(viewModelScope)
    }


    // liveData
    fun getDetailByFlow(id: Int) {
        useCase(id).onEach { result ->

            when (result) {
                is Resource.Loading -> {
                    _responseStateFlow.value = DetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _responseStateFlow.value = DetailState(data = result.data!!)
                }
                is Resource.Error -> {
                    _responseStateFlow.value = DetailState(error = "An unexpected error occured" )
                }
            }

        }.launchIn(viewModelScope)
    }

}