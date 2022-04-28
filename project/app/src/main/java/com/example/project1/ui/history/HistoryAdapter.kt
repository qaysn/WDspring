package com.example.project1.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1.R
import com.example.project1.base.ParentViewHolder
import com.example.project1.data.models.CatInfoRoom
import java.lang.StringBuilder


class HistoryAdapter() :
    RecyclerView.Adapter<ParentViewHolder>() {

    private val VIEW_TYPE_ERROR = 0
    private val VIEW_TYPE_NORMAL = 1

    private var isLoaderVisible = false
// адаптер для история лист
    private val catsList = ArrayList<CatInfoRoom>()


    fun clearAll() {
        catsList.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): CatInfoRoom? {
        return catsList[position]
    }

    fun addItems(list: List<CatInfoRoom>) {
        catsList.addAll(list)
        notifyDataSetChanged()
    }
// подготовка каждого айтема в листе
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> CatsViewHolder(
                inflater.inflate(R.layout.adapter_cat_history, parent, false)
            )
            else -> throw Throwable("invalid view")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CatInfoRoom -> {
                return VIEW_TYPE_NORMAL
            }
            else -> {
                return VIEW_TYPE_ERROR
            }
        }
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        if (holder is CatsViewHolder) {
            val cat = catsList[position]
            holder.bind(cat)
        }
    }
//обычный вью холдер для айтема
    inner class CatsViewHolder(view: View) : ParentViewHolder(view) {
        private val tvCatInfo: TextView
        private val ivPhoto: ImageView

        init {
            tvCatInfo = view.findViewById(R.id.tv_cat_info)
            ivPhoto = view.findViewById(R.id.iv_photo)
        }

        fun bind(cat: CatInfoRoom) {

            val strBuilder = StringBuilder()
                .append("id: " + cat?.id + "\n")
                .append("url: " + cat?.id + "\n")
                .append("WxH: " + cat?.width + ":" + cat?.height + "\n")
            tvCatInfo.text = strBuilder

            Glide.with(itemView.context)
                .load(cat.url)
                .into(ivPhoto)

        }
        override fun clear() {}
    }

}