package com.mpgcode.reactivemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mpgcode.reactivemvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenToViewModel()
    }

    private fun listenToViewModel() {
        lifecycleScope.launch {
            // Don't forget to import kotlinx.coroutines.flow.collect
            viewModel.state.flowWithLifecycle(lifecycle).collect { render(it) }
        }
    }

    private fun render(state: MainViewState) = with(binding) {
        author.text = state.author
        quote.text = state.quote
        quoteBtn.isEnabled = state.isButtonEnabled
        shareBtn.isEnabled = state.isButtonEnabled
        spinner.visibility = state.spinnerVisibility.value
    }
}