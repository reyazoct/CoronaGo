package com.reyaz.coronago.statewise.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.reyaz.coronago.databinding.StateWiseItemBinding
import com.reyaz.coronago.statewise.models.Statewise

class StateWiseItemAdapter(
    private val stateWise: List<Statewise>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<StateWiseItemAdapter.StateWiseItemVH>(), Filterable {

    private var stateWiseList: List<Statewise> = stateWise

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateWiseItemVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = StateWiseItemBinding.inflate(inflater, parent, false)
        return StateWiseItemVH(view)
    }

    override fun getItemCount() = stateWiseList.size

    override fun onBindViewHolder(holder: StateWiseItemVH, position: Int) {
        val stateWise = stateWiseList[position]
        holder.binding.data = stateWise
        holder.binding.root.setOnClickListener {
            listener.onClick(stateWise, getSharedElements(holder.binding))
        }
    }

    private fun getSharedElements(binding: StateWiseItemBinding): Bundle {
        return Bundle().apply {
            putSerializable(
                SHARED_ELEMENTS,
                arrayOf(
                    Pair.create(binding.stateNameTv, STATE_NAME),
                    Pair.create(binding.confirmedLayout.root, STATE_WISE_VIEW_1),
                    Pair.create(binding.activeLayout.root, STATE_WISE_VIEW_2),
                    Pair.create(binding.recoveredLayout.root, STATE_WISE_VIEW_3),
                    Pair.create(binding.deathsLayout.root, STATE_WISE_VIEW_4)
                )
            )
        }
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

    interface OnItemClickListener {
        fun onClick(stateWise: Statewise, bundle: Bundle? = null)
    }

    companion object {
        const val SHARED_ELEMENTS = "SHARED_ELEMENTS"
        const val STATE_NAME = "STATE_NAME"
        const val STATE_WISE_VIEW_1 = "STATE_WISE_VIEW_1"
        const val STATE_WISE_VIEW_2 = "STATE_WISE_VIEW_2"
        const val STATE_WISE_VIEW_3 = "STATE_WISE_VIEW_3"
        const val STATE_WISE_VIEW_4 = "STATE_WISE_VIEW_4"
    }
}
