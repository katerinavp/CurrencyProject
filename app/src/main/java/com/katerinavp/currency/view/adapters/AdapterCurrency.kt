package com.katerinavp.currency.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katerinavp.currency.data.db.model.CurrencyDbModel
import com.katerinavp.currency.databinding.RecyclerviewItemBinding


class AdapterCurrency : ListAdapter<CurrencyDbModel, AdapterCurrency.CurrencyViewHolder>(
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

        fun bind(currency: CurrencyDbModel) {
            with(binding) {
                ticker.text = currency.code
                nameCurrency.text = currency.name
                value.text = currency.value.toString()
            }
        }

        fun partialBind(currency: CurrencyDbModel) {
            binding.value.text = currency.value.toString()
        }
    }


}
