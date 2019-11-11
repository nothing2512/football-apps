package com.github.nothing2512.football_v2.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.ActivityFavoriteBinding
import com.github.nothing2512.football_v2.utils.getBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val lovedViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getBinding(R.layout.activity_favorite)

        binding.activity = this
        binding.lifecycleOwner = this
        binding.viewModel = lovedViewModel
    }
}
