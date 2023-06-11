package com.katerinavp.currency.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currency.databinding.RecyclerviewItemBinding
import com.katerinavp.currency.model.data.ConvertCurrency


class AdapterCurrency : ListAdapter<ConvertCurrency, AdapterCurrency.CurrencyViewHolder>(
    ModelCurrencyDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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

    class CurrencyViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: ConvertCurrency) {
            with(binding) {
                ticker.text = currency.codeCurrency
                nameCurrency.text = currency.nameCurrency
                value.text = currency.valueCurrency.toString()
            }
        }

        fun partialBind(currency: ConvertCurrency) {
            binding.value.text = currency.valueCurrency.toString()
        }
    }


}
