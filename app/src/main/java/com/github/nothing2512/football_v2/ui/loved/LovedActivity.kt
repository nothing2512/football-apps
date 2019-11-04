package com.github.nothing2512.football_v2.ui.loved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.ActivityLovedBinding
import com.github.nothing2512.football_v2.utils.getBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LovedActivity : AppCompatActivity() {

    private val lovedViewModel: LovedViewModel by viewModel()

    private lateinit var binding: ActivityLovedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getBinding(R.layout.activity_loved)

        binding.activity = this
        binding.lifecycleOwner = this
        binding.viewModel = lovedViewModel
    }
}
