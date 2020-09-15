package com.home.androiddatastoredemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.home.androiddatastoredemo.model.HomeRepository
import com.home.androiddatastoredemo.model.HomeResponse
import com.home.androiddatastoredemo.model.HomeViewState
import kotlinx.coroutines.flow.combine

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val viewStateFlow = combine(
        repository.tasks,
        repository.enteredPhoneNumber
    ) { response: HomeResponse, enteredPhoneNumber: String ->
        val correctPhoneNumber = response.correctPhoneNumber
        val isRight = correctPhoneNumber == enteredPhoneNumber
        return@combine HomeViewState(
            correctPhoneNumber = correctPhoneNumber,
            enteredPhoneNumber = enteredPhoneNumber,
            isRight = isRight
        )
    }
    val viewStateLiveData = viewStateFlow.asLiveData()

    fun setEnteredPhoneNumber(enteredPhoneNumber: String) {
        repository.enteredPhoneNumber.value = enteredPhoneNumber
    }
}
