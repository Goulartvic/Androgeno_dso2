package br.ufsc.victor.brasilnacopa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_cronograma.*

class ScheduleActivity : AppCompatActivity() {

    private lateinit var recyclerViewJogos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma)

        val jogosList = GamesSchedule.getCronogramaFromFile("jogos.json", this)
        recyclerViewJogos = recycler_jogos

        recyclerViewJogos.adapter = GamesScheduleListAdapter(this, jogosList)
        recyclerViewJogos!!.layoutManager = LinearLayoutManager(this)

    }
}
