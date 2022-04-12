package com.example.recyclerview_newsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        //to convert XML into itemView we use LayoutInflater class
        Log.d("onCreateViewHolder","ON CREATE VIEW HOLDER CALLED")
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        //click listener
        view.setOnClickListener{
//            Toast.makeText(parent.context,"clicked",Toast.LENGTH_SHORT).show()
            listener.onItemClicked(items[viewHolder.adapterPosition])
            //this is a callback hence can be accessed in parent
        }

        ////
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //takes each item and binds it....i.e takes data and fill it in
        Log.d("onBindViewHolder","ON CREATE VIEW HOLDER CALLED")

        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)
//        holder.titleView.text=currentItem.title
//        holder.titleView.text=currentItem.title
    }
    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll((updatedNews))

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView:TextView=itemView.findViewById(R.id.title)
    val image:ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)
//    val titleView:TextView=itemView.findViewById(R.id.title)
}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}