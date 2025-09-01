package com.katerinavp.currencies_screen_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currencies_screen_impl.databinding.RecyclerviewItemBinding
import com.katerinavp.currency_api.model.CurrencyDomainModel


class AdapterCurrency(private val saveFavorites: (currency: CurrencyDomainModel, position: Int) -> Unit) : ListAdapter<CurrencyDomainModel, AdapterCurrency.CurrencyViewHolder>(
    ModelCurrencyDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), saveFavorites
        )

    //перерисовать полностью данных
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(private val binding: RecyclerviewItemBinding,
    private val saveFavorites: (currency: CurrencyDomainModel,position: Int)->Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyDomainModel) {
            with(binding) {
                currencyInfo.ticker = currency.code
                currencyInfo.name = currency.name
                currencyInfo.value = currency.value.toString()
                currencyInfo.isFavorites = currency.isFavorites
            }

            itemView.setOnLongClickListener {
                saveFavorites.invoke(currency, adapterPosition)
                return@setOnLongClickListener true
                }
        }

       fun updateFavorites(item: CurrencyDomainModel) = with(binding) {
           currencyInfo.isFavorites = (item.isFavorites)
           }
    }


}
