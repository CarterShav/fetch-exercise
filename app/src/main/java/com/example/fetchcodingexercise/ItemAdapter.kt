/*
    Item Adapter file for Fetch Rewards Coding Exercise designed to handle information
    for a recycler view
    author: Carter Shavitz
    date: 7/9/2024
 */
package com.example.fetchcodingexercise

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter class to handle the information provided to the recycler view.
 */
class ItemAdapter(private val list: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    /**
     * Viewholder class to allow for row and header holder to be referenced in same location
     */
    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open lateinit var idRowHeader : TextView
        open lateinit var listIdRowHeader : TextView
        open lateinit var nameRowHeader : TextView
        open lateinit var idHeader : TextView
        open lateinit var listIdHeader : TextView
        open lateinit var nameHeader : TextView
    }

    /**
     * Retrieve correct text views for each object.
     */
    class RowViewHolder(view: View) : ViewHolder(view) {
        override var idRowHeader : TextView = itemView.findViewById(R.id.id_row_header)
        override var listIdRowHeader : TextView = itemView.findViewById(R.id.listid_row_header)
        override var nameRowHeader : TextView = itemView.findViewById(R.id.name_row_header)
    }

    /**
     * Retrieve correct text views for each object.
     */
    class HeaderViewHolder(view: View) : ViewHolder(view) {
        override var idHeader : TextView = itemView.findViewById(R.id.id_header)
        override var listIdHeader : TextView = itemView.findViewById(R.id.listid_header)
        override var nameHeader : TextView = itemView.findViewById(R.id.name_header)
    }

    /**
     * Enum class to label a row a row or header.
     */
    enum class RowType {
        HEADER,
        ROW
    }

    /**
     * Activated on creation of view holder, inflates a view while checking if it is row or header.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = when (viewType) {
            RowType.ROW.ordinal -> layoutInflater.inflate(R.layout.text_row_item, parent, false)
            else -> layoutInflater.inflate(R.layout.text_header, parent, false)
        }
        return when (viewType) {
            RowType.ROW.ordinal -> RowViewHolder(view)
            else -> HeaderViewHolder(view)
        }
    }

    /**
     * On bind of the view holder, update the color of the row, as well as update the string.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        //Update color on position.
        if (position % 2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFF6F6F6"))
        }

        //Update content based on value.
        if (item is Item.Content) {
            holder.idRowHeader.text = item.id.toString()
            holder.listIdRowHeader.text = item.listId.toString()
            holder.nameRowHeader.text = item.name
        } else if (item is Item.Header){
            holder.idHeader.text = item.idHeader
            holder.listIdHeader.text = item.listHeader
            holder.nameHeader.text = item.nameHeader
        }
    }

    /**
     * Retrieve the type of the item.
     */
    override fun getItemViewType(position: Int): Int {
        return if (list[position] is Item.Header) 0 else 1
    }

    /**
     * get total count of items, can be useful for debugging.
     */
    override fun getItemCount() = list.size
}