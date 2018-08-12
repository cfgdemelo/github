package br.com.cfgdemelo.github.model

import java.io.Serializable

class Pull: Serializable {
    var id: Int? = null
    var html_url: String? = null
    var user: User? = null
    var title: String? = null
    var created_at: String? = null
    var body: String? = null
}