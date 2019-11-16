package com.github.nothing2512.football_v2.ui.league.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.KlasemenEntity
import com.github.nothing2512.football_v2.databinding.DialogKlasemenBinding
import com.github.nothing2512.football_v2.ui.league.adapter.KlasemenAdapter
import com.github.nothing2512.football_v2.utils.getBinding

class KlasemenDialog(
    activity: Activity,
    parent: ViewGroup,
    klasemen: List<KlasemenEntity>
) : AlertDialog.Builder(ContextThemeWrapper(activity, R.style.DescriptionDialog)) {

    private val binding: DialogKlasemenBinding = getBinding(R.layout.dialog_klasemen, parent)
    private var alertDialog: AlertDialog

    init {

        setView(binding.root)

        binding.klasemenList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            adapter = KlasemenAdapter(klasemen)
            clearFocus()
        }

        binding.dummyView.requestFocus()

        alertDialog = this.create()

        binding.swipe.apply {
            setOnRefreshListener {
                isRefreshing = false
                alertDialog.dismiss()
            }
            setColorSchemeResources(android.R.color.transparent)
        }

        binding.btDown.setOnClickListener { alertDialog.dismiss() }

        alertDialog.apply {

            requestWindowFeature(Window.FEATURE_NO_TITLE)

            window?.apply {

                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                attributes?.apply {

                    windowAnimations = R.style.DescriptionDialog

                    gravity = Gravity.BOTTOM

                    flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                }
            }
        }
    }

    override fun show(): AlertDialog {
        alertDialog.show()
        return alertDialog
    }
}