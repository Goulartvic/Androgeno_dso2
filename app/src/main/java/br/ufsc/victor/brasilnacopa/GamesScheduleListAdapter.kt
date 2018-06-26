package br.ufsc.victor.brasilnacopa

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.jogos_list_item.view.*

class GamesScheduleListAdapter(private val context: Context,
                               private val jogosList: ArrayList<GamesSchedule>) : RecyclerView.Adapter<GamesScheduleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.jogos_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jogosList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogos = jogosList[position]

        holder.let {
            it.bindView(jogos)
        }
        holder.setOnCustomItemClickListener(object : CustomItemClickListener {
            override fun onCustomItemClickListener(view: View, pos: Int) {
                val mapsIntent = MapsActivity.newIntent(context, jogosList[pos])
                context.startActivity(mapsIntent)
            }
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var customItemClickListener: CustomItemClickListener? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun setOnCustomItemClickListener(itemClickListener: CustomItemClickListener) {
            this.customItemClickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            this.customItemClickListener!!.onCustomItemClickListener(v!!, adapterPosition)
        }

        fun bindView(gamesSchedule: GamesSchedule) {
            val data = itemView.jogos_list_data
            val horario = itemView.jogos_list_horario
            val local = itemView.jogos_list_local
            val versus = itemView.jogos_list_versus

            data.text = gamesSchedule.data
            horario.text = gamesSchedule.horario
            local.text = gamesSchedule.local
            versus.text = gamesSchedule.versus
        }
    }
}