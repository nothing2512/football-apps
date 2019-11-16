package com.github.nothing2512.football_v2.ui.team.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.appcompat.app.AlertDialog
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.databinding.DialogStadiumBinding
import com.github.nothing2512.football_v2.utils.getBinding

class StadiumDialog(
    activity: Activity,
    parent: ViewGroup,
    data: TeamEntity?
) : AlertDialog.Builder(ContextThemeWrapper(activity, R.style.DescriptionDialog)) {

    private val binding: DialogStadiumBinding = getBinding(R.layout.dialog_stadium, parent)
    private var alertDialog: AlertDialog

    init {

        setView(binding.root)

        alertDialog = this.create()

        binding.team = data

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