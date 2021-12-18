package com.mpgcode.reactivemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mpgcode.reactivemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}