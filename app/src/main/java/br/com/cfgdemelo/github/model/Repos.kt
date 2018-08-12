package br.com.cfgdemelo.github.model

import java.io.Serializable

class Repos: Serializable {
    var total_count: Int? = null
    var incomplete_results: Boolean? = null
    var items: ArrayList<Repo>? = null
}