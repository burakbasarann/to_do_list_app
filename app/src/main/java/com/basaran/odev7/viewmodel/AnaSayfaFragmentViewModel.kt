package com.basaran.odev7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.basaran.odev7.entity.Yapilacaklar
import com.basaran.odev7.repo.YapilacaklarDaoRepository

class AnaSayfaFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()
    val yrepo = YapilacaklarDaoRepository(application)

    init {
        yapilacaklariYukle()
        yapilacaklarListesi = yrepo.yapilacaklariGetir()
    }

    fun ara(aramaKelimesi: String) {
        yrepo.yapilacakAra(aramaKelimesi)
    }

    fun sil(yapilacaklar_id: Int) {
        yrepo.yapilacakSil(yapilacaklar_id)
    }

    fun yapilacaklariYukle() {
        yrepo.tumYapilacaklariAl()
    }

}