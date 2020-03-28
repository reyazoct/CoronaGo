package com.reyaz.coronago.app

import androidx.multidex.MultiDexApplication

class CoronaGoApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CoronaGoApplication
    }
}