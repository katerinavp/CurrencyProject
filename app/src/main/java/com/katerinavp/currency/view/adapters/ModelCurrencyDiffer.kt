package com.katerinavp.currency.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katerinavp.currency.model.data.ConvertCurrency

object ModelCurrencyDiffer : DiffUtil.ItemCallback<ConvertCurrency>() {

    override fun areItemsTheSame(oldItem: ConvertCurrency, newItem: ConvertCurrency): Boolean =
        oldItem.codeCurrency == newItem.codeCurrency ||   oldItem.nameCurrency == newItem.nameCurrency

    override fun areContentsTheSame(oldItem: ConvertCurrency, newItem: ConvertCurrency): Boolean =
        oldItem.valueCurrency == newItem.valueCurrency
}
