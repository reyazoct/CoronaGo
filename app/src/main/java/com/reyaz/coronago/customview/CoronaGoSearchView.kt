package com.reyaz.coronago.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.reyaz.coronago.R
import com.reyaz.coronago.databinding.CoronaGoSearchViewBinding
import com.reyaz.coronago.utils.openKeyboard

class CoronaGoSearchView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
    View.OnClickListener {

    private val binding: CoronaGoSearchViewBinding
    private var queryListener: QueryListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.corona_go_search_view, this, true)
        initListeners()
    }

    private fun initListeners() {
        binding.closeIv.setOnClickListener(this)
        binding.queryEt.addTextChangedListener {
            queryListener?.onQueryChange(it.toString())
            binding.closeIv.visibility = if(it.toString().isBlank()) View.GONE else View.VISIBLE
        }
    }

    fun setQueryListener(listener: QueryListener) {
        queryListener = listener
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.close_iv -> onCloseButtonClick()
        }
    }

    private fun onCloseButtonClick() {
        binding.queryEt.text.clear()
    }

    fun openSearch() {
        binding.queryEt.openKeyboard(context)
    }

    interface QueryListener {
        fun onQueryChange(query: String);
    }
}