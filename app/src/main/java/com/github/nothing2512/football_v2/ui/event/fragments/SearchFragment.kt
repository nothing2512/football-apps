package com.github.nothing2512.football_v2.ui.event.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.FragmentSearchBinding
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding
    private var query: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, R.layout.fragment_search, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchMain {

            binding.searchRecyclerView.apply {
                adapter = EventAdapter()
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }

            if (query.isNotEmpty()) getData(query)
        }
    }

    fun setQuery(query: String?) {
        try {
            getData(query ?: "")
            this.query = ""
        } catch (e: Exception) {
            this.query = query ?: ""
        }
    }

    private fun getData(query: String) {

        eventViewModel.searchEvent(query).observe(this@SearchFragment, Observer {

            when (it.status) {

                Status.ERROR -> {
                    binding.searchShimmer.hide()
                    binding.searchRecyclerView.show()

                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    binding.searchShimmer.hide()
                    binding.searchRecyclerView.show()

                    it.data?.event?.let { search ->
                        binding.searchRecyclerView.adapter = EventAdapter(search)
                    }
                }

                Status.LOADING -> {

                    binding.searchShimmer.show()
                    binding.searchRecyclerView.hide()
                    binding.searchRecyclerView.adapter = EventAdapter(ArrayList())
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
