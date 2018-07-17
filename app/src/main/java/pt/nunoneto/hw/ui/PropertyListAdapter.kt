package pt.nunoneto.hw.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.nunoneto.hw.R
import pt.nunoneto.hw.entities.Property

class PropertyListAdapter(context: Context?) : RecyclerView.Adapter<PropertyListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    var properties: List<Property> = ArrayList()

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.property_list_item, container, false))
    }

    override fun getItemCount(): Int = properties.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}