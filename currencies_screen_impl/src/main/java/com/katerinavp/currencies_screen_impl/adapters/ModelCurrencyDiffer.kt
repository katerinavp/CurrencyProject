package com.katerinavp.currencies_screen_impl.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katerinavp.currency_api.model.CurrencyDomainModel

object ModelCurrencyDiffer : DiffUtil.ItemCallback<CurrencyDomainModel>() {

    override fun areItemsTheSame(
        oldItem: CurrencyDomainModel,
        newItem: CurrencyDomainModel
    ): Boolean {
        return oldItem.code == newItem.code
    }


    override fun areContentsTheSame(
        oldItem: CurrencyDomainModel,
        newItem: CurrencyDomainModel
    ): Boolean =
        oldItem.code == newItem.code
}
