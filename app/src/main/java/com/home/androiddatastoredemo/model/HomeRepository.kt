package com.home.androiddatastoredemo.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

object HomeRepository {

    var enteredPhoneNumber = MutableStateFlow("")
    val tasks = flowOf(HomeResponse("0988123123"))
}
