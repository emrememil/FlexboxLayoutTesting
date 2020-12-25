package com.example.flextest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class SelectedRecyclerViewAdapter() : RecyclerView.Adapter<SelectedRecyclerViewAdapter.SelectedItemViewHolder>() {

    val mDiffer = AsyncListDiffer<Data>(this, SelectedDataListDiffer())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedRecyclerViewAdapter.SelectedItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_field, parent, false)
        return SelectedItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SelectedItemViewHolder, position: Int) {
        val data = mDiffer.currentList[position]
        holder.onBind(position)

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let {
                    it(data!!,holder.itemView)
                }
            }
        }
    }

    fun setData(data: List<Data>?) {
        mDiffer.submitList(data)
    }

    inner class SelectedItemViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.field_item_text)
        fun onBind(position: Int) {
            mDiffer.currentList?.let {dataList->
                text.text = dataList[position].name
            }

        }
    }

    private var onItemClickListener: ((Data, View) -> Unit)? = null

    fun setOnItemClickListener(listener: (Data, View) -> Unit) {
        onItemClickListener = listener
    }
}

class SelectedDataListDiffer : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}
