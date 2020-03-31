package com.reyaz.coronago.statespecific.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.reyaz.coronago.R
import com.reyaz.coronago.databinding.ActivityStateSpecificBinding
import com.reyaz.coronago.statewise.activities.StateWiseActivity.Companion.STATEWISE
import com.reyaz.coronago.statewise.adapters.StateSpecificAdapter
import com.reyaz.coronago.statewise.models.Statewise
import com.reyaz.coronago.statewise.viewmodels.StateWiseVM

class StateSpecificActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStateSpecificBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(StateWiseVM::class.java) }
    private var statewiseData: Statewise? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_specific)
        initUi()
        initObservers()
    }

    private fun initObservers() {
        viewModel.stateSpecificData.observe(this, Observer { data ->
            val jsonObject = JsonParser().parse(data) as JsonObject
            val stateData =
                (jsonObject.get(statewiseData?.state) as? JsonObject)?.get(DISTRICT_DATA) as? JsonObject

            val stateSpecificList = mutableListOf<Pair<String, String>>()
            stateData?.entrySet()?.forEach { pair ->
                fetchConfirmedCases(pair.value)?.let {
                    stateSpecificList.add(Pair(pair.key, it))
                }
            }
            binding.stateSpecificLayout.recyclerView.adapter =
                StateSpecificAdapter(stateSpecificList)
        })
    }

    private fun fetchConfirmedCases(jsonElement: JsonElement): String? {
        return (jsonElement as JsonObject).get(CONFIRMED).toString()
    }

    private fun initUi() {
        window.sharedElementEnterTransition.duration = 1000
        window.sharedElementReturnTransition.duration = 1000
        viewModel.fetchStateSpecificData()
        intent?.getParcelableExtra<Statewise>(STATEWISE)?.let {
            binding.topBar.data = it
            statewiseData = it
        }
    }

    companion object {
        private const val DISTRICT_DATA = "districtData"
        private const val CONFIRMED = "confirmed"
    }
}
