package com.reyaz.coronago.statewise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.reyaz.coronago.databinding.StateWiseItemBinding
import com.reyaz.coronago.statewise.models.Statewise

class StateWiseItemAdapter(private val stateWise: List<Statewise>) :
    RecyclerView.Adapter<StateWiseItemAdapter.StateWiseItemVH>(), Filterable {

    private var stateWiseList: List<Statewise> = stateWise

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateWiseItemVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = StateWiseItemBinding.inflate(inflater, parent, false)
        return StateWiseItemVH(view)
    }

    override fun getItemCount() = stateWiseList.size

    override fun onBindViewHolder(holder: StateWiseItemVH, position: Int) {
        holder.binding.data = stateWiseList[position]
    }

    override fun getFilter() = StateWiseFilter()

    inner class StateWiseFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val results = FilterResults()
            if (charSequence.isEmpty()) {
                results.values = stateWise
                results.count = stateWise.count()
            } else {
                stateWise.filter {
                    it.state?.contains(charSequence, true) ?: false
                }.let {
                    results.values = it
                    results.count = it.count()
                }
            }
            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            (filterResults.values as? List<Statewise>)?.let {
                stateWiseList = it
            }
            notifyDataSetChanged()
        }
    }

    class StateWiseItemVH(val binding: StateWiseItemBinding) : RecyclerView.ViewHolder(binding.root)
}
