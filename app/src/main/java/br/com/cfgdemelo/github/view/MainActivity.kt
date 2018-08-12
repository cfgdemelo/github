package br.com.cfgdemelo.github.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cfgdemelo.github.R
import br.com.cfgdemelo.github.presenter.MainPresenter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainPresenter(this).loadRecyclerView(findViewById(R.id.mainRecyclerView))
    }

}
