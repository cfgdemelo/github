package br.com.cfgdemelo.github.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cfgdemelo.github.R
import br.com.cfgdemelo.github.presenter.PRPresenter

class PRActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr)
        val intent = intent
        val path: String = intent.getStringExtra("path")
        PRPresenter(this, path).loadRecyclerView(findViewById(R.id.prRecyclerView))
        title = "PRs of $path"
    }
}
