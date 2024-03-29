package com.github.nothing2512.football_v2.ui.team.dialog

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.databinding.DialogTeamBinding
import com.github.nothing2512.football_v2.ui.team.adapter.TeamAdapter
import com.github.nothing2512.football_v2.ui.team.activity.SearchTeamActivity
import com.github.nothing2512.football_v2.utils.getBinding

class TeamDialog(
    activity: Activity,
    parent: ViewGroup,
    teams: List<TeamEntity>
) : AlertDialog.Builder(ContextThemeWrapper(activity, R.style.DescriptionDialog)) {

    private val binding: DialogTeamBinding = getBinding(R.layout.dialog_team, parent)
    private var alertDialog: AlertDialog

    init {

        setView(binding.root)

        binding.teamList.apply {
            layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            adapter = TeamAdapter(teams)
            clearFocus()
        }

        binding.btSearchTeam.setOnClickListener {
            activity.startActivity(Intent(activity, SearchTeamActivity::class.java))
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