package com.basaran.odev7.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.basaran.odev7.entity.Yapilacaklar
import com.basaran.odev7.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YapilacaklarDaoRepository(var application: Application) {
    var yapilacaklarListesi: MutableLiveData<List<Yapilacaklar>>
    var vt: Veritabani

    init {
        yapilacaklarListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!
    }

    fun yapilacakAra(aramaKelimesi: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = vt.yapilacaklarDao().yapilacakIsArama(aramaKelimesi)
        }
    }

    fun yapilacakKayit(yapilacak_ad: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniYapilacakIs = Yapilacaklar(0, yapilacak_ad)
            vt.yapilacaklarDao().yapilacakIsEkle(yeniYapilacakIs)
        }
    }

    fun yapilacakGuncelle(yapilacak_id: Int, yapilacak_ad: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenIs = Yapilacaklar(yapilacak_id, yapilacak_ad)
            vt.yapilacaklarDao().yapilacakIsGuncelle(guncellenenIs)
        }
    }

    fun yapilacakSil(yapilacak_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinecekIs = Yapilacaklar(yapilacak_id, "")
            vt.yapilacaklarDao().yapilacakIsSil(silinecekIs)
            tumYapilacaklariAl()
        }
    }

    fun yapilacaklariGetir(): MutableLiveData<List<Yapilacaklar>> {
        return yapilacaklarListesi
    }

    fun tumYapilacaklariAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = vt.yapilacaklarDao().tumYapilacaklar()
        }
    }


}