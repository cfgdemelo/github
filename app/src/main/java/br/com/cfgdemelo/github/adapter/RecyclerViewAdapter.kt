package br.com.cfgdemelo.github.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.cfgdemelo.github.R
import br.com.cfgdemelo.github.model.Repo
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private var context: Context, private var repoList: ArrayList<Repo>, private var itemClick: RepoClickListener): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun getItemCount(): Int {
        return repoList.size
    }

    interface RepoClickListener {
        fun getItem(context: Context, repo: Repo)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(context, repoList, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_list, parent, false)
        return RecyclerViewHolder(view, itemClick)
    }

    class RecyclerViewHolder(itemView: View, private var itemClick: RepoClickListener) : RecyclerView.ViewHolder(itemView) {
        var userImage: ImageView = itemView.findViewById(R.id.userImage)
        var txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
        var txtRepoName: TextView = itemView.findViewById(R.id.txtRepoName)
        var txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        var txtForks: TextView = itemView.findViewById(R.id.txtForks)
        var txtStars: TextView = itemView.findViewById(R.id.txtStars)

        fun bindData(context: Context, repoList: ArrayList<Repo>, position: Int) {
            Picasso.get().load(repoList[position].owner?.avatar_url).into(userImage)
            txtUserName.text = repoList[position].owner?.login
            txtRepoName.text = repoList[position].name
            txtDescription.text = repoList[position].description
            txtForks.text = repoList[position].forks.toString()
            txtStars.text = repoList[position].watchers.toString()
            itemView.setOnClickListener {
                itemClick.getItem(context, repoList[position])
            }
        }
    }
}
