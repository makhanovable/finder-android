package kz.hacknu.findroom.presentation.interest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.i_check_box.view.*
import kz.hacknu.findroom.R
import kz.hacknu.findroom.data.room.Interests
import org.koin.core.KoinComponent
import org.koin.core.inject

class EnumCheckBoxListAdapter(val context : Context) : RecyclerView.Adapter<EnumCheckBoxViewHolder>(), KoinComponent {
//    private val settingsRepo: SettingsRepo by inject()

    private var items = mutableListOf<Pair<Boolean, Interests>>()
    fun submitData(data: MutableList<Pair<Boolean, Interests>>) {
        this.items = data
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<Interests> {
        val res = arrayListOf<Interests>()
        for (item in items)
            if (item.first) res.add(item.second)
        return res
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnumCheckBoxViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.i_check_box, parent, false) //todo use another layout
        return EnumCheckBoxViewHolder(view)
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: EnumCheckBoxViewHolder, position: Int) {
        holder.itemView.container_ll.setOnClickListener {
            if (holder.itemView.checkBox.isChecked) {
                changeToSelectedItemState(position, isSelected = false)
                holder.itemView.checkBox.isChecked = false
            } else {
                changeToSelectedItemState(position, isSelected = true)
                holder.itemView.checkBox.isChecked = true
            }
        }
        holder.itemView.checkBox.isChecked = items[position].first
        holder.itemView.title_tv.text = items[position].second.toString()



        // if (isSelected(items[position].first))

        // todo
    }

    private fun changeToSelectedItemState(position: Int, isSelected: Boolean) {
        val currentPair = items[position]
        val newCurrentPair = Pair(isSelected, currentPair.second)

        items.removeAt(position)
        items.add(position, newCurrentPair)
        notifyDataSetChanged()
    }
}

class EnumCheckBoxViewHolder(view: View) : RecyclerView.ViewHolder(view)