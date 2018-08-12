package br.com.cfgdemelo.github.model

import java.io.Serializable

class User: Serializable {
    var id: Int? = null
    var login: String? = null
    var avatar_url: String? = null
}