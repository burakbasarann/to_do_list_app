package com.basaran.odev7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.basaran.odev7.repo.YapilacaklarDaoRepository

class IsGuncelleFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val yrepo = YapilacaklarDaoRepository(application)

    fun guncelle(yapilacak_id: Int, yapilacak_ad: String) {
        yrepo.yapilacakGuncelle(yapilacak_id, yapilacak_ad)
    }
}