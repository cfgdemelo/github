package br.com.cfgdemelo.github.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    fun getRepoList(@Query("q") q: String,
                    @Query("sort") sort: String,
                    @Query("page") page: Int): Call<Repos>


    @GET("repos/{path}/pulls")
    fun getPullList(@Path("path", encoded = true) path: String) : Call<ArrayList<Pull>>
}