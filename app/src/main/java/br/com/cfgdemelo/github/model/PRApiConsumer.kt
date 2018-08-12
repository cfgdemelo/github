package br.com.cfgdemelo.github.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PRApiConsumer {
    private var baseUrl: String = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    fun apiInterface() = retrofit.create(PRApiInterface::class.java)
}