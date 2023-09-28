package com.katerinavp.favorites_screen_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.favorites_screen.databinding.RecyclerviewFavoritesItemBinding


class AdapterFavorites(private val deleteFavorite: (currency: CurrencyDomainModel, position: Int) -> Unit) : RecyclerView.Adapter<AdapterFavorites.FavoritesViewHolder>(
) {

    private val items = mutableListOf<CurrencyDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            RecyclerviewFavoritesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), deleteFavorite
        )

    override fun getItemCount(): Int {
        return items.size
    }

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
//        holder.bind(getItem(position))
        holder.bind(items[position])
    }

    fun addAll(newItem: List<CurrencyDomainModel>) {
        val diffCallback = ModelFavoritesDiffer(items, newItem)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItem)

        diffResult.dispatchUpdatesTo(this)
    }

    class FavoritesViewHolder(private val binding: RecyclerviewFavoritesItemBinding, private val deleteFavorite: (currency: CurrencyDomainModel, position: Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyDomainModel) {
            with(binding) {
                favoritesInfo.ticker = currency.code
                favoritesInfo.name = currency.name
                favoritesInfo.value = currency.value.toString()
            }

            itemView.setOnLongClickListener {
                deleteFavorite.invoke(currency, adapterPosition)
                return@setOnLongClickListener true
            }
        }



//        fun partialBind(currency: CurrencyDomainModel) {
//            binding.favoritesInfo.value = currency.value.toString()
//        }
    }



    fun removeAt(index: Int) {
        items.removeAt(index)   // items is a MutableList
        notifyItemRemoved(index)
    }


}

