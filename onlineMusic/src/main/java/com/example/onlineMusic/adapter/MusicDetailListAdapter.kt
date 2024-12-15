package com.example.onlineMusic.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineMusic.R
import com.example.onlineMusic.bean.*
import com.example.onlineMusic.receiver.FragmentBroadCastReceiver
import com.example.onlineMusic.utils.OnlinePlaying
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_detail.*


class MusicDetailListAdapter(val context: Context, val detailMusicList:List<Music>):RecyclerView.Adapter<MusicDetailListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val musicImage: ImageView =view.findViewById(R.id.musicDetailImage)
        val musicName: TextView =view.findViewById(R.id.musicDetailName)
        val musicPlayer: TextView =view.findViewById(R.id.playerNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //注意这里需要设置子项的布局文件，否则报错
        val view= LayoutInflater.from(parent.context).inflate(R.layout.music_list_details,parent,false)
        val viewHolder=ViewHolder(view)
        //注册子项的点击事件
        viewHolder.itemView.setOnClickListener {
            //设置当前播放的音乐
            val music=detailMusicList[viewHolder.adapterPosition]
            OnlinePlaying.changeMusic(viewHolder.adapterPosition)

            //发送打开item的广播
            val intent = Intent(FragmentBroadCastReceiver.ACTION_FRAGMENT_PLAY_DETAILSONG)
            intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            context.sendBroadcast(intent)

        }

        return viewHolder
    }

    override fun getItemCount(): Int=detailMusicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val music: Music = detailMusicList[position]
        holder.musicName.text=music.filmName.substringBefore('-')
        holder.musicImage.setImageResource(music.filmImageId)
        holder.musicPlayer.text=music.filmName.substringAfter('-')
    }
}