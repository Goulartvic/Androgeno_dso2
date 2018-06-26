package br.ufsc.victor.brasilnacopa

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(private val context: Context,
                      private val newsList: ArrayList<News>,
                      private val newsActivity: NewsActivity) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false)

        return ViewHolder(view, context, newsActivity)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]

        holder.let {
            it.bindView(news)
        }
    }

    class ViewHolder(itemView: View, context: Context, activity: NewsActivity) : RecyclerView.ViewHolder(itemView) {

        val con = context

        val act = activity

        val API_KEY = "AIzaSyDiGJuSdpzFPnWOcYw4HWHNS58u5w_q4SQ"

        fun bindView(news: News) {
            val titulo = itemView.news_list_titulo
            val descricao = itemView.news_list_description
            val imagem = itemView.news_list_image
            val youtube = itemView.button

            titulo.text = news.title
            descricao.text = news.description
            Picasso.with(con).load(news.imageUrl).placeholder(R.mipmap.ic_launcher_round).into(imagem)
            youtube.setOnClickListener({
                val intent = YouTubeStandalonePlayer.createVideoIntent(act, API_KEY, news.youtubeLink)
                con.startActivity(intent)
            })
        }
    }
}