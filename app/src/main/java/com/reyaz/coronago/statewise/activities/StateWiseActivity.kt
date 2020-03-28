package com.reyaz.coronago.statewise.activities

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.reyaz.coronago.R
import com.reyaz.coronago.base.BaseActivity
import com.reyaz.coronago.databinding.ActivityStatewiseBinding
import com.reyaz.coronago.statewise.adapters.StateWiseItemAdapter
import com.reyaz.coronago.statewise.models.KeyValue
import com.reyaz.coronago.statewise.viewmodels.StateWiseVM
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormat

class StateWiseActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StateWiseVM::class.java) }
    private lateinit var binding: ActivityStatewiseBinding
    private var adapter: StateWiseItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statewise)
        initError()
        initUi()
        initListeners()
        initObservers()
    }

    private fun initError() {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            finish()
        })
    }

    private fun initListeners() {
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter?.filter?.filter(newText)
        return true
    }

    private fun initObservers() {
        viewModel.coronaData.observe(this, Observer { data ->
            data.statewise?.let { statewiseList ->
                binding.loadingLayout.loadingSfl.stopShimmerAnimation()
                binding.loadingLayout.loadingSfl.visibility = View.GONE
                binding.stateRv.visibility = View.VISIBLE
                val onlyStateList = statewiseList.toMutableList().apply {
                    removeAll { it.state == TOTAL }
                }

                adapter = StateWiseItemAdapter(onlyStateList)
                binding.stateRv.adapter = adapter

                statewiseList.firstOrNull { statewise ->
                    statewise.state == TOTAL
                }?.let { binding.topBar.data = it }

                data.keyValues?.firstOrNull()?.let {
                    showLastUpdatedTime(it)
                }
            }
        })
    }

    private fun showLastUpdatedTime(it: KeyValue) {
        val dateTime = DateTime.parse(
            it.lastUpdatedTime,
            DateTimeFormat.forPattern(DATE_TIME_FORMAT)
        )
        val period = Period(dateTime, DateTime())

        PeriodFormat.getDefault().print(period).split(",").firstOrNull()
            ?.let { lastUpdated ->
                binding.topBar.lastUpdatedTv.text =
                    getString(R.string.last_updated, lastUpdated)
            } ?: run {
            binding.topBar.lastUpdatedTv.visibility = View.GONE
        }
    }

    private fun initUi() {
        binding.loadingLayout.loadingSfl.startShimmerAnimation()
        viewModel.fetchCoronaData()
    }

    companion object {
        private const val TOTAL = "Total"
        private const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"
    }
}
