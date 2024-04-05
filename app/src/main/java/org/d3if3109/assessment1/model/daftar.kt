package org.d3if3109.assessment1.model

import androidx.annotation.DrawableRes

data class daftar(
    val judul: String,
    val catatan: String,
    val jenis: String,
    @DrawableRes val gambarResId: Int? = null
)
