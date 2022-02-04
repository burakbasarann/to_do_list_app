package com.basaran.odev7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.basaran.odev7.databinding.CardTasarimBinding
import com.basaran.odev7.entity.Yapilacaklar
import com.basaran.odev7.fragment.AnaSayfaFragmentDirections
import com.basaran.odev7.viewmodel.AnaSayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class YapilacaklarAdapter(var mContext: Context,
                          var yapilacaklarListesi: List<Yapilacaklar>,
                          var viewModel: AnaSayfaFragmentViewModel) : RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: CardTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root) {
        var cardTasarimBinding: CardTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yapilacak = yapilacaklarListesi.get(position)
        val tasarim = holder.cardTasarimBinding
        tasarim.yapilacakNesnesi = yapilacak

        tasarim.satirCard.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.isGuncelleGecis(yapilacak)
            Navigation.findNavController(it).navigate(gecis)
        }

        tasarim.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${yapilacak.yapilacak_is} Silinsin mi", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.sil(yapilacak.yapilacak_id)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }
}