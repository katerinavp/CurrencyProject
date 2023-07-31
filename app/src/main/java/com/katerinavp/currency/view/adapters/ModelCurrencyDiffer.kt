package com.katerinavp.currency.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katerinavp.currency.data.db.model.CurrencyDbModel

object ModelCurrencyDiffer : DiffUtil.ItemCallback<CurrencyDbModel>() {

    override fun areItemsTheSame(oldItem: CurrencyDbModel, newItem: CurrencyDbModel): Boolean =
        oldItem == newItem||   oldItem == newItem

    override fun areContentsTheSame(oldItem: CurrencyDbModel, newItem: CurrencyDbModel): Boolean =
        oldItem == newItem
}
