package com.reyaz.coronago.statespecific.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.reyaz.coronago.R
import com.reyaz.coronago.databinding.ActivityStateSpecificBinding
import com.reyaz.coronago.statewise.activities.StateWiseActivity.Companion.STATEWISE
import com.reyaz.coronago.statewise.models.Statewise

class StateSpecificActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStateSpecificBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_specific)
        initUi()
    }

    private fun initUi() {
        window.sharedElementEnterTransition.duration = 1000
        window.sharedElementReturnTransition.duration = 1000
        intent?.getParcelableExtra<Statewise>(STATEWISE)?.let {
            binding.topBar.data = it
        }
    }
}
