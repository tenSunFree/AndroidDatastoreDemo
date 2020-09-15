package com.home.androiddatastoredemo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.home.androiddatastoredemo.databinding.ActivityHomeBinding
import com.home.androiddatastoredemo.model.HomeRepository
import com.home.androiddatastoredemo.viewModel.HomeViewModel
import com.home.androiddatastoredemo.viewModel.HomeViewModelFactory
import androidx.lifecycle.observe

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.viewBottom.setOnClickListener {
            val enteredPhoneNumber = binding.editText.text.toString()
            viewModel.setEnteredPhoneNumber(enteredPhoneNumber)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, HomeViewModelFactory(HomeRepository)
        ).get(HomeViewModel::class.java)
        viewModel.viewStateLiveData.observe(this) { tasksUiModel ->
            if (tasksUiModel.isRight) {
                val text = "號碼正確！"
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            } else if (tasksUiModel.enteredPhoneNumber.isNotEmpty()) {
                val text = "請輸入正確的號碼，0988123123。"
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
