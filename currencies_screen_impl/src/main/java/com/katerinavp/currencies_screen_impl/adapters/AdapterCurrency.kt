package com.katerinavp.currencies_screen_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currencies_screen_impl.databinding.RecyclerviewItemBinding
import com.katerinavp.currency_api.model.CurrencyDomainModel


class AdapterCurrency(private val openGraphic: (id: Int) -> Unit,
private val saveFavorites: (currency: CurrencyDomainModel) -> Unit) : ListAdapter<CurrencyDomainModel, AdapterCurrency.CurrencyViewHolder>(
    ModelCurrencyDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),openGraphic, saveFavorites
        )

    //перерисовать часть данных на UI
    override fun onBindViewHolder(
        holder: CurrencyViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        holder.partialBind(getItem(position))
    }

    //перерисовать полностью данных
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(private val binding: RecyclerviewItemBinding, private val openGraphic: (graphic:Int)->Unit,
    private val saveFavorites: (currency: CurrencyDomainModel)->Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyDomainModel) {
            with(binding) {
                ticker.text = currency.code
                nameCurrency.text = currency.name
                value.text = currency.value.toString()
            }

            itemView.setOnClickListener {
                openGraphic.invoke(1)
            }

            itemView.setOnLongClickListener {
                saveFavorites.invoke(currency)
                return@setOnLongClickListener true}
        }

        fun partialBind(currency: CurrencyDomainModel) {
            binding.value.text = currency.value.toString()
        }
    }


}
