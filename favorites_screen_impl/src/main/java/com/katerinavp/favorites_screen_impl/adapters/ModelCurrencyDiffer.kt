package com.katerinavp.favorites_screen_impl.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katerinavp.currency_api.model.CurrencyDomainModel

object ModelCurrencyDiffer : DiffUtil.ItemCallback<CurrencyDomainModel>() {

    override fun areItemsTheSame(
        oldItem: CurrencyDomainModel,
        newItem: CurrencyDomainModel
    ): Boolean =
        oldItem == newItem || oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CurrencyDomainModel,
        newItem: CurrencyDomainModel
    ): Boolean =
        oldItem == newItem
}
