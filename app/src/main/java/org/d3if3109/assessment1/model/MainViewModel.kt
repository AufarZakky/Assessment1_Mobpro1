package org.d3if3109.assessment1.model

import androidx.lifecycle.ViewModel
import org.d3if3109.assessment1.R
import org.d3if3109.assessment1.model.daftar

class MainViewModel : ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<daftar> {
        val data = mutableListOf<daftar>()
        data.add(daftar("kubus", "Hitung volume bangun ruang kubus", "kubus", R.drawable.kubus1))
        data.add(daftar("tabung", "Hitung volume bangun ruang tabung", "tabung",R.drawable.tabung1))
        data.add(daftar("balok", "Hitung volume bangun ruang balok", "balok",R.drawable.balok1))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))
        data.add(daftar("Data belum tersedia", "", "kosong"))

        return data
    }
}
