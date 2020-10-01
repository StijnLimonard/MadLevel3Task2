package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.models.Portal
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalsAdapter (private val portals: List<Portal>, private val clickListener: (Portal) -> Unit) : RecyclerView.Adapter<PortalsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun dataBind(portal: Portal, clickListener: (Portal) -> Unit) {
            itemView.tvPortalTitle.text = portal.title
            itemView.tvPortalUrl.text = portal.url
            itemView.setOnClickListener { clickListener(portal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(portals[position], clickListener)
    }

    override fun getItemCount(): Int {
        return portals.size
    }
}