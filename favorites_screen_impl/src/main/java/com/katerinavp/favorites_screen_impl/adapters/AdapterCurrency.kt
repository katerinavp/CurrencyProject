package com.katerinavp.favorites_screen_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.favorites_screen.databinding.RecyclerviewFavoritesItemBinding


class AdapterFavorites() : ListAdapter<CurrencyDomainModel, AdapterFavorites.FavoritesViewHolder>(
    ModelCurrencyDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            RecyclerviewFavoritesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    //перерисовать часть данных на UI
//    override fun onBindViewHolder(
//        holder: FavoritesViewHolder,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        super.onBindViewHolder(holder, position, payloads)
//        holder.partialBind(getItem(position))
//    }

    //перерисовать полностью данных
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoritesViewHolder(private val binding: RecyclerviewFavoritesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyDomainModel) {
            with(binding) {
                favoritesInfo.ticker = currency.code
                favoritesInfo.name = currency.name
                favoritesInfo.value = currency.value.toString()
            }
        }


//        fun partialBind(currency: CurrencyDomainModel) {
//            binding.favoritesInfo.value = currency.value.toString()
//        }
    }


}
