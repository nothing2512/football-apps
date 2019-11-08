package com.github.nothing2512.football_v2.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nothing2512.football_v2.ui.view.FavoriteActivityUI
import org.jetbrains.anko.setContentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val lovedViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoriteActivityUI(lovedViewModel).setContentView(this)
    }
}
