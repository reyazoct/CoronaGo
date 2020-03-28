package com.reyaz.coronago.statewise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reyaz.coronago.databinding.StateWiseItemBinding
import com.reyaz.coronago.statewise.models.Statewise

class StateWiseItemAdapter(private val stateWise: List<Statewise>) :
    RecyclerView.Adapter<StateWiseItemAdapter.StateWiseItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateWiseItemVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = StateWiseItemBinding.inflate(inflater, parent, false)
        return StateWiseItemVH(view)
    }

    override fun getItemCount() = stateWise.size

    override fun onBindViewHolder(holder: StateWiseItemVH, position: Int) {
        holder.binding.data = stateWise[position]
    }

    class StateWiseItemVH(val binding: StateWiseItemBinding) : RecyclerView.ViewHolder(binding.root)
}
