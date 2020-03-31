package com.reyaz.coronago.statewise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reyaz.coronago.databinding.StateSpecificItemBinding

class StateSpecificAdapter(private val itemList: List<Pair<String, String>>) :
    RecyclerView.Adapter<StateSpecificAdapter.StateSpecificVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateSpecificVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = StateSpecificItemBinding.inflate(inflater, parent, false)
        return StateSpecificVH(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: StateSpecificVH, position: Int) {
        holder.binding.labelTv.text = itemList[position].first
        holder.binding.dataTv.text = itemList[position].second
    }

    class StateSpecificVH(val binding: StateSpecificItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}