package com.github.nothing2512.football_v2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.ActivityHomeBinding
import com.github.nothing2512.football_v2.ui.favorite.FavoriteActivity
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_home)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = homeViewModel

        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        binding.btHomeLove.setOnClickListener {
            startActivity(Intent(applicationContext, FavoriteActivity::class.java))
        }

        binding.swipe.setOnRefreshListener {

            homeViewModel.refresh()
            binding.swipe.isRefreshing = false
            binding.btBackHome.show()
            binding.imBackground.requestFocus()
        }

        binding.imBackground.requestFocus()

        binding.searchView.apply {

            setOnQueryTextFocusChangeListener { _, hasFocus ->

                if (hasFocus && query.isNullOrEmpty() && !binding.searchView.isVisible)
                    clearFocus()
                if (!hasFocus && !binding.btBackHome.isVisible) isIconified = true
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {

                    homeViewModel.setQuery(query ?: "")
                    homeViewModel.submitQuery()
                    if (!query.isNullOrEmpty()) binding.btBackHome.show()
                    binding.imBackground.requestFocus()
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?) = true

            })
        }
    }

    override fun onBackPressed() {

        if (binding.btBackHome.isVisible) {
            homeViewModel.setQuery("")
            homeViewModel.submitQuery()
            launchMain {
                binding.btBackHome.hide()
                binding.searchView.clearFocus()
                binding.searchView.isIconified = true
            }
        } else finish()
    }
}
