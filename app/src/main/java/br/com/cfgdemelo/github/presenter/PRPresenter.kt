package br.com.cfgdemelo.github.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import br.com.cfgdemelo.github.adapter.PRRecyclerViewAdapter
import br.com.cfgdemelo.github.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PRPresenter(private var context: Context, private var path: String): PRRecyclerViewAdapter.PullClickListener {

    override fun getItem(context: Context, pull: Pull) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = pull.html_url
        intent.data = Uri.parse(url)
        startActivity(context, intent, null)
    }

    var pullsData: ArrayList<Pull> = ArrayList()
    fun loadRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadApi(recyclerView, context)
    }

    private fun loadApi(recyclerView: RecyclerView, context: Context) {
        val apiInterface: PRApiInterface = PRApiConsumer().apiInterface()
        val call: Call<ArrayList<Pull>> = apiInterface.getPullList(path)
        call.enqueue(object: Callback<ArrayList<Pull>> {
            override fun onResponse(call: Call<ArrayList<Pull>>, response: Response<ArrayList<Pull>>) {
                pullsData.addAll(response.body()!!)
                val adapter = recyclerView.adapter
                if (adapter == null) {
                    recyclerView.adapter = PRRecyclerViewAdapter(context, pullsData, this@PRPresenter)
                } else {
                    adapter.notifyDataSetChanged()
                }
            }

            @SuppressLint("ShowToast")
            override fun onFailure(call: Call<ArrayList<Pull>>?, t: Throwable?) {
                Toast.makeText(context, "Erro ao recuperar Informações", Toast.LENGTH_SHORT).show()
            }
        })
    }

}