package br.com.cfgdemelo.github.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PRApiInterface {
    @GET("repos/{path}/pulls")
    fun getPullList(@Path("path", encoded = true) path: String) : Call<ArrayList<Pull>>
}