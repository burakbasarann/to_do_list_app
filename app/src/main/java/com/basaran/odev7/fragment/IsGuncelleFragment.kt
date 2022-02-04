package com.basaran.odev7.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.basaran.odev7.R
import com.basaran.odev7.databinding.FragmentIsGuncelleBinding
import com.basaran.odev7.viewmodel.IsGuncelleFragmentViewModel
import com.basaran.odev7.viewmodel.IsGuncelleVMF


class IsGuncelleFragment : Fragment() {

    private lateinit var tasarim: FragmentIsGuncelleBinding
    private lateinit var viewModel: IsGuncelleFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_is_guncelle, container, false)
        tasarim.isGuncelleFragment = this
        tasarim.isGuncelleToolbarBaslik = "Yapılacak İş Detay"

        val bundle: IsGuncelleFragmentArgs by navArgs()
        val gelenIs = bundle.yapilacaklar

        tasarim.yapilacakNesnesi = gelenIs

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: IsGuncelleFragmentViewModel by viewModels() {
            IsGuncelleVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonGuncelleTikla(yapilacal_id: Int, yapilacak_ad: String) {
        viewModel.guncelle(yapilacal_id, yapilacak_ad)
    }
}