package com.basaran.odev7.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.basaran.odev7.R
import com.basaran.odev7.adapter.YapilacaklarAdapter
import com.basaran.odev7.databinding.FragmentAnaSayfaBinding
import com.basaran.odev7.entity.Yapilacaklar
import com.basaran.odev7.viewmodel.AnaSayfaFragmentViewModel
import com.basaran.odev7.viewmodel.AnasayfaVMF


class AnaSayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim: FragmentAnaSayfaBinding
    private lateinit var adapter: YapilacaklarAdapter
    private lateinit var viewModel: AnaSayfaFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_ana_sayfa, container, false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = "Yapılacaklar"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnaSayfa)

        viewModel.yapilacaklarListesi.observe(viewLifecycleOwner, {
            adapter = YapilacaklarAdapter(requireContext(), it, viewModel)
            tasarim.yapilacaklarAdapter = adapter
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: AnaSayfaFragmentViewModel by viewModels() {
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.action_ara)
        val searhView = item.actionView as SearchView
        searhView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

    fun fabTikla(v: View) {
        Navigation.findNavController(v).navigate(R.id.isKayitGecis)
    }

    override fun onResume() {
        super.onResume()
        viewModel.yapilacaklariYukle()
    }

}