package br.com.cfgdemelo.github.model

import java.io.Serializable

class Repo: Serializable {
    var id: Int? = null
    var name: String? = null
    var full_name: String? = null
    var description: String? = null
    var watchers: String? = null
    var forks: Int? = null
    var owner: User? = null
}