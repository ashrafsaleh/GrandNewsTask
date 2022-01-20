package com.example.grandnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grandnews.R
import com.example.grandnews.data.remote.DataX
import com.example.grandnews.model.OnArticleListener
import com.squareup.picasso.Picasso

class ArticleAdapter(private val savedArticles: List<DataX>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleImage: ImageView = itemView.findViewById(R.id.newsimage)
        var articleTitle: TextView = itemView.findViewById(R.id.newstitle)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return ArticleAdapter.ViewHolder(v)
    }

    private var onItemClickListener: OnArticleListener? = null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (savedArticles[position].secure_media==null)
        {
            holder.articleImage.setImageResource(R.drawable.noimage)

        }else {
            Picasso.get().load(savedArticles[position].secure_media!!.oembed!!.thumbnail_url).into(holder.articleImage)
        }


       // holder.articleImage.setImageResource(R.drawable.newsimagetest)

        holder.itemView.apply {
            holder.articleTitle.text = savedArticles[position].title
            setOnClickListener {
                onItemClickListener?.onclick(savedArticles[position]!!)

            }
        }
    }

    override fun getItemCount() = savedArticles.size

    fun setOnItemClickListener(listener: OnArticleListener) {
        onItemClickListener = listener
    }

}