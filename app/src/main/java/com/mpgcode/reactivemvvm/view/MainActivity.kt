package com.mpgcode.reactivemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mpgcode.reactivemvvm.R

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}