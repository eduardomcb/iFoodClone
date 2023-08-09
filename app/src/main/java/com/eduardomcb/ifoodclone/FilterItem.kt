package com.eduardomcb.ifoodclone

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.chip.Chip

data class FilterItem(
    val id: Int,
    val text: String,
    @DrawableRes val icon: Int? = null,
    val iconSize: Float = 32.0f,
    @DrawableRes val closeIcon: Int? = null,
)

fun FilterItem.toChip(context: Context): Chip {
    val chip = if (closeIcon == null) {
        LayoutInflater.from(context).inflate(R.layout.chip_choice, null, false) as Chip
    } else {
        Chip(ContextThemeWrapper(context, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice))
    }

    if (closeIcon != null)
        chip.setChipBackgroundColorResource(R.color.white)

    chip.setChipStrokeColorResource(R.color.gray)

    chip.chipStrokeWidth = 2f
    if (icon != null) {
        chip.chipIconSize = iconSize
        chip.setChipIconResource(icon)
        chip.chipStartPadding = 20f
    } else {
        chip.chipIcon = null
    }
    closeIcon?.let {
        chip.setCloseIconResource(it)
        chip.isCloseIconVisible = true
    }

    chip.text = text
    return chip
}
