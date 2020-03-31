package com.reyaz.coronago.statewise.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reyaz.coronago.base.BaseVM
import com.reyaz.coronago.statewise.models.CoronaData
import com.reyaz.coronago.statewise.repositories.StateWiseRepository
import kotlinx.coroutines.launch

class StateWiseVM : BaseVM() {
    private val repository by lazy { StateWiseRepository() }

    private val _coronaData = MutableLiveData<CoronaData>()
    val coronaData: LiveData<CoronaData>
        get() = _coronaData

    private val _stateSpecificData = MutableLiveData<String>()
    val stateSpecificData: LiveData<String>
        get() = _stateSpecificData

    fun fetchCoronaData() {
        coroutineScope.launch {
            val response = repository.fetchCoronaData()
            Log.i("Corona Data", response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    _coronaData.value = it
                }
            }
        }
    }

    fun fetchStateSpecificData() {
        coroutineScope.launch {
            val response = repository.fetchStateSpecificData()
            Log.i("State Specific Data", response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    _stateSpecificData.value = it.string()
                }
            }
        }
    }
}