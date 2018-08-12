package br.com.cfgdemelo.github.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.cfgdemelo.github.R
import br.com.cfgdemelo.github.model.Pull
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PRRecyclerViewAdapter(private var context: Context, private var pullList: ArrayList<Pull>, private var itemClick: PullClickListener): RecyclerView.Adapter<PRRecyclerViewAdapter.PRRecyclerViewHolder>() {

    override fun getItemCount(): Int {
        return pullList.size
    }

    interface PullClickListener {
        fun getItem(context: Context, pull: Pull)
    }

    override fun onBindViewHolder(holder: PRRecyclerViewHolder, position: Int) {
        holder.bindData(context, pullList, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PRRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pull_item_list, parent, false)
        return PRRecyclerViewHolder(view, itemClick)
    }

    class PRRecyclerViewHolder(itemView: View, private var itemClick: PullClickListener) : RecyclerView.ViewHolder(itemView) {
        var userImage: ImageView = itemView.findViewById(R.id.userImage)
        var txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
        var txtPullName: TextView = itemView.findViewById(R.id.txtPullName)
        var txtPullBody: TextView = itemView.findViewById(R.id.txtPullBody)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
        var dfBr: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        var dateBr: String? = null

        fun bindData(context: Context, pullList: ArrayList<Pull>, position: Int) {
            Picasso.get().load(pullList[position].user?.avatar_url).into(userImage)
            txtUserName.text = pullList[position].user?.login
            txtPullName.text = pullList[position].title
            txtPullBody.text = pullList[position].body
            val date: Date = df.parse(pullList[position].created_at)
            dateBr = dfBr.format(date)
            txtDate.text = dateBr
            itemView.setOnClickListener {
                itemClick.getItem(context, pullList[position])
            }
        }
    }
}
