package com.example.flextest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UnselectedDataRecyclerViewAdapter(): RecyclerView.Adapter<UnselectedDataRecyclerViewAdapter.UnselectedItemViewHolder>() {

    val mDiffer = AsyncListDiffer<Data>(this, UnselectedDataListDiffer())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnselectedDataRecyclerViewAdapter.UnselectedItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_choose, parent, false)
        return UnselectedItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UnselectedItemViewHolder, position: Int) {
        val data = mDiffer.currentList[position]
        holder.onBind(position)

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let {
                    it(data!!)
                }
            }
        }
    }

    fun setData(data: List<Data>?) {
        mDiffer.submitList(data)
    }

    inner class  UnselectedItemViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.choose_item_text)

        fun onBind(position: Int) {
            mDiffer.currentList?.let {
                text.text = it[position].name

            }
        }
    }
    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}

class UnselectedDataListDiffer : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}