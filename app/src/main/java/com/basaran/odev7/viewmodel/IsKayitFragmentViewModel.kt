package com.basaran.odev7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.basaran.odev7.repo.YapilacaklarDaoRepository

class IsKayitFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val yrepo = YapilacaklarDaoRepository(application)

    fun kayit(yapilacak_ad: String) {
        yrepo.yapilacakKayit(yapilacak_ad)
    }
}