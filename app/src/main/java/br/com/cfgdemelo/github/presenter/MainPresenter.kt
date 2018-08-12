package br.com.cfgdemelo.github.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import br.com.cfgdemelo.github.adapter.RecyclerViewAdapter
import br.com.cfgdemelo.github.model.ApiConsumer
import br.com.cfgdemelo.github.model.ApiInterface
import br.com.cfgdemelo.github.model.Repo
import br.com.cfgdemelo.github.model.Repos
import br.com.cfgdemelo.github.view.PRActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private var context: Context): RecyclerViewAdapter.RepoClickListener {

    var isLoading: Boolean = false
    var page: Int = 1
    var q: String = "language:Java"
    var sort: String = "stars"

    override fun getItem(context: Context, repo: Repo) {
        val intent = Intent(context, PRActivity::class.java)
        val path: String? = repo.full_name
        intent.putExtra("path", path)
        startActivity(context, intent, null)
    }

    var repos: Repos? = null
    var reposData: ArrayList<Repo> = ArrayList()
    fun loadRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount: Int = recyclerView!!.layoutManager.childCount
                    val totalItemCount: Int = recyclerView.layoutManager.itemCount
                    val firstVisible: Int = linearLayoutManager.findFirstVisibleItemPosition()
                    if (!isLoading && visibleItemCount + firstVisible >= totalItemCount) {
                        loadApi(recyclerView, context)
                    }
                }
            }
        })
        loadApi(recyclerView, context)
    }

    private fun loadApi(recyclerView: RecyclerView, context: Context) {
        isLoading = true
        val apiInterface: ApiInterface = ApiConsumer().apiInterface()
        val call: Call<Repos> = apiInterface.getRepoList(q, sort, page)
        call.enqueue(object: Callback<Repos> {
            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                repos = response.body()
                reposData.addAll(repos?.items!!)
                val adapter = recyclerView.adapter
                if (adapter == null) {
                    recyclerView.adapter = RecyclerViewAdapter(context, reposData, this@MainPresenter)
                } else {
                    adapter.notifyDataSetChanged()
                }
                isLoading = false
                ++page
            }

            @SuppressLint("ShowToast")
            override fun onFailure(call: Call<Repos>?, t: Throwable?) {
                Toast.makeText(context, "Erro ao recuperar Informações", Toast.LENGTH_SHORT).show()
            }
        })
    }

}