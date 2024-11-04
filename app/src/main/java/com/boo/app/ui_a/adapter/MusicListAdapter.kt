package com.boo.app.ui_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boo.app.R
import com.boo.app.ui_a.bean.MusicBean

class MusicListAdapter(private val context: Context, private val musicList: List<MusicBean>) :
    RecyclerView.Adapter<MusicListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val musicBean = musicList.get(position)
        holder.tvName.text = musicBean.mMusicName
        holder.tvSinger.text = musicBean.mMusicArtist
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvSinger: TextView = itemView.findViewById(R.id.tv_singer)
        var ivMore: ImageView = itemView.findViewById(R.id.iv_more)
        var ivStatus: ImageView = itemView.findViewById(R.id.iv_status)
    }
}