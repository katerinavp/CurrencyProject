package com.katerinavp.favorites_screen_impl.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katerinavp.currency_api.model.CurrencyDomainModel

class ModelFavoritesDiffer(
    private val oldItem: List<CurrencyDomainModel>,
    private val newItem: List<CurrencyDomainModel>
) : DiffUtil.Callback() {

//    override fun areItemsTheSame(
//        oldItem: CurrencyDomainModel,
//        newItem: CurrencyDomainModel
//    ): Boolean =
//        oldItem.id == newItem.id
//
//    override fun areContentsTheSame(
//        oldItem: CurrencyDomainModel,
//        newItem: CurrencyDomainModel
//    ): Boolean =
//        oldItem.value == newItem.value

    override fun getOldListSize() = oldItem.size

    override fun getNewListSize() = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition].code == newItem[newItemPosition].code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return oldItem.code == newItem.code &&
                oldItem.value == newItem.value
    }

}
