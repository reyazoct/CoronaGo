package com.reyaz.coronago.statewise.activities

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.reyaz.coronago.R
import com.reyaz.coronago.base.BaseActivity
import com.reyaz.coronago.databinding.ActivityStatewiseBinding
import com.reyaz.coronago.statewise.adapters.StateWiseItemAdapter
import com.reyaz.coronago.statewise.viewmodels.StateWiseVM

class StateWiseActivity : BaseActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StateWiseVM::class.java) }
    private lateinit var binding: ActivityStatewiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statewise)
        initUi()
        initObservers()
    }

    private fun initObservers() {
        viewModel.coronaData.observe(this, Observer { data ->
            data.statewise?.let { statewiseList ->
                binding.loadingLayout.loadingSfl.stopShimmerAnimation()
                binding.loadingLayout.loadingSfl.visibility = View.GONE
                binding.stateRv.visibility = View.VISIBLE
                binding.stateRv.adapter = StateWiseItemAdapter(statewiseList)

                statewiseList.firstOrNull { statewise ->
                    statewise.state == TOTAL
                }?.let {

                }
            }
        })
    }

    private fun getColorDrawable(@ColorRes colorId: Int): Int {
        return ContextCompat.getColor(applicationContext, colorId)
    }

    private fun initUi() {
        binding.loadingLayout.loadingSfl.startShimmerAnimation()
        viewModel.fetchCoronaData()
    }

    companion object {
        private const val TOTAL = "Total"
    }
}
