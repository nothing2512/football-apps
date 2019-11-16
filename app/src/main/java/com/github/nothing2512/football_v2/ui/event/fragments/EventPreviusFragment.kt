package com.github.nothing2512.football_v2.ui.event.fragments


import android.content.Context
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
import com.github.nothing2512.football_v2.databinding.FragmentEventBinding
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventPreviusFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding

    private val eventViewModel: EventViewModel by viewModel()
    private var idLeague = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, R.layout.fragment_event, container)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchMain {

            idLeague = arguments?.getInt(Constants.EXTRA_ID) ?: 0

            binding.eventRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }

            eventViewModel.loadPreviusEvent(idLeague).observe(this@EventPreviusFragment, Observer {

                when (it.status) {

                    Status.SUCCESS -> {

                        stopLoading()
                        it.data?.events?.let { search ->
                            binding.eventRecyclerView.adapter = EventAdapter(search)
                        }
                    }

                    Status.ERROR -> {

                        stopLoading()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }

                    Status.LOADING -> startLoading()
                }
            })
        }
    }

    private fun startLoading() {

        binding.eventShimmer.start()
        binding.eventRecyclerView.hide()
    }

    private fun stopLoading() {

        binding.eventRecyclerView.clearFocus()
        binding.eventShimmer.stop()
        binding.eventRecyclerView.show()
    }

    override fun onAttach(context: Context) {
        retainInstance = true
        super.onAttach(context)
    }

    companion object {

        @JvmStatic
        fun newInstance(idLeague: Int) = EventPreviusFragment().apply {
            val bundle = Bundle().apply {
                putInt(Constants.EXTRA_ID, idLeague)
            }
            arguments = bundle
        }
    }
}
