package com.github.nothing2512.football_v2.ui.loved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nothing2512.football_v2.ui.view.LovedActivityUI
import org.jetbrains.anko.setContentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class LovedActivity : AppCompatActivity() {

    private val lovedViewModel: LovedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LovedActivityUI(lovedViewModel).setContentView(this)
    }
}
