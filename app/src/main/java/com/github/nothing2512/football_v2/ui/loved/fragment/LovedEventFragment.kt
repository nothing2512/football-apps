package com.github.nothing2512.football_v2.ui.loved.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.FragmentEventBinding
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.ui.loved.LovedViewModel
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.utils.stop
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LovedEventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding

    private val lovedViewModel: LovedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, R.layout.fragment_event, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lovedViewModel.getEvents().observe(this, Observer {
            launchMain {
                binding.eventShimmer.stop()
                binding.eventRecyclerView.show()
                binding.eventRecyclerView.apply {
                    isNestedScrollingEnabled = true
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                    adapter = EventAdapter(it)
                    clearFocus()
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = LovedEventFragment()
    }
}