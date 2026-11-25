package com.example.cervezas.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cervezas.R
import com.example.cervezas.databinding.ItemBeerBinding
import com.example.domain.model.Item

class BeerAdapter(
    private val onBeerClick: (Item) -> Unit
) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    private var beers: List<Item> = emptyList()

    fun submitList(newList: List<Item>) {
        beers = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beers[position])
    }

    override fun getItemCount(): Int = beers.size

    inner class ViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(beer: Item) {
            binding.beerName.text = beer.name
            binding.beerImage.load(beer.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
            binding.root.setOnClickListener {
                onBeerClick(beer)
            }
        }
    }
}
