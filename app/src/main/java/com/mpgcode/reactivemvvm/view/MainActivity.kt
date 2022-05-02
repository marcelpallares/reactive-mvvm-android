package com.mpgcode.reactivemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mpgcode.reactivemvvm.databinding.ActivityMainBinding
import com.mpgcode.reactivemvvm.view.utils.shareText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        quoteBtn.setOnClickListener { viewModel.emitAction(QuoteButtonClick) }
        shareBtn.setOnClickListener { viewModel.emitAction(ShareButtonClick(
            author = author.text.toString(),
            quote = quote.text.toString()
        )) }
    }

    private fun listenToViewModel() {
        lifecycleScope.launch {
            // Don't forget to import kotlinx.coroutines.flow.collect
            viewModel.state.flowWithLifecycle(lifecycle).collect { render(it) }
        }
        lifecycleScope.launch {
            viewModel.viewEvents.flowWithLifecycle(lifecycle).collect { handleEvent(it) }
        }
    }

    private fun render(state: MainViewState) = with(binding) {
        author.text = state.author
        quote.text = state.quote
        quoteBtn.isEnabled = state.isButtonEnabled
        shareBtn.isEnabled = state.isButtonEnabled
        spinner.visibility = state.spinnerVisibility.value
    }

    private fun handleEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.Share -> shareText(event.text)
        }
    }
}