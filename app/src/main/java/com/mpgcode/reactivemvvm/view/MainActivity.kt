package com.mpgcode.reactivemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mpgcode.reactivemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        listenToViewModel()
    }

    private fun setupUI() = with(binding) {

    }

    private fun listenToViewModel() {

    }
}