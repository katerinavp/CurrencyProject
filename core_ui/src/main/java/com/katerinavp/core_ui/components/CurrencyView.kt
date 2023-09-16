package com.katerinavp.core_ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.katerinavp.core_ui.R


class CurrencyView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val tickerTextView: TextView
    private val nameTextView: TextView
    private val valueTextView: TextView
    private val favoritesView: ImageView


    var ticker: CharSequence?
        get() {
            return tickerTextView.text
        }
        set(value) {
            tickerTextView.text = value
        }

    var name: CharSequence?
        get() {
            return nameTextView.text
        }
        set(value) {
            nameTextView.text = value
        }

    var value: CharSequence?
        get() {
            return valueTextView.text
        }
        set(value) {
            valueTextView.text = value
        }


    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.CurrencyView, 0, 0)

        val tickerStyle = a.getResourceId(R.styleable.CurrencyView_tickerStyle, com.katerinavp.currency_api.R.style.Text_Large_Bold)
        val nameStyle = a.getResourceId(R.styleable.CurrencyView_tickerStyle, com.katerinavp.currency_api.R.style.Text)
        val drawableId =
            a.getResourceId(R.styleable.CurrencyView_favorites, -1)
        val favorites =  if (drawableId != -1) AppCompatResources.getDrawable(context, drawableId) else null
        val ticker = a.getString(R.styleable.CurrencyView_ticker)
        val name = a.getString(R.styleable.CurrencyView_name)
        val value = a.getString(R.styleable.CurrencyView_value)
        val textMargin = a.getDimensionPixelSize(R.styleable.CurrencyView_textMargin, 0)

        a.recycle()


        tickerTextView = TextView(ContextThemeWrapper(context, tickerStyle))
        nameTextView = TextView(ContextThemeWrapper(context, nameStyle))
        valueTextView = TextView(ContextThemeWrapper(context, tickerStyle))
        favoritesView = ImageView(ContextThemeWrapper(context, tickerStyle))

        tickerTextView.id = R.id.ticker
        nameTextView.id = R.id.name
        favoritesView.id = R.id.favorites


        tickerTextView.text = ticker
        nameTextView.text = name
        valueTextView.text = value

        initTicker(textMargin)
        initName(textMargin)
        initValue(textMargin)
        initFavorites(favorites, textMargin)

        addView(tickerTextView)
        addView(nameTextView)
        addView(valueTextView)
        addView(favoritesView)

    }

    private fun initTicker(textMargin: Int) {
        val lpt = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            startToStart = LayoutParams.PARENT_ID
            endToStart = nameTextView.id
            topToTop = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID

        }
        tickerTextView.layoutParams = lpt
    }

    private fun initName(textMargin: Int) {
        val lpt = LayoutParams(450, LayoutParams.WRAP_CONTENT).apply {
            startToEnd = tickerTextView.id
            endToStart = valueTextView.id
            topToTop = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID
            marginStart = textMargin
        }
        nameTextView.layoutParams = lpt
    }

    private fun initValue(textMargin: Int) {
        val lpt = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            startToEnd = nameTextView.id
            topToTop = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID
            endToStart = favoritesView.id
            marginStart = textMargin
        }
        valueTextView.layoutParams = lpt
    }

    private fun initFavorites(icon: Drawable?, iconMargin: Int) {
        val lpt = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            endToEnd = LayoutParams.PARENT_ID
            topToTop = LayoutParams.PARENT_ID
            bottomToBottom = LayoutParams.PARENT_ID
            marginEnd = iconMargin/2
        }
        favoritesView.layoutParams = lpt
        if (icon == null) {
            favoritesView.visibility = View.GONE
        } else {
            favoritesView.setImageDrawable(icon)
            favoritesView.setBackgroundResource(R.drawable.baseline_favorite_border_24)
        }
    }

}