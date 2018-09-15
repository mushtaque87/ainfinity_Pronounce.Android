package ainfinity.com.pronounce.application.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import  android.view.View
import android.view.ViewGroup
import ainfinity.com.pronounce.application.R
import kotlinx.android.synthetic.main.contentlist_item.view.*

class ContentViewAdapter: RecyclerView.Adapter<ContentViewHolder>() {

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.view.titleTextView.text =  "Title"
        holder.view.createdDateTextView.text = "Created On:"
        holder.view.submissionDateTextView.text = "Submitted on:"
        holder.view.descriptionTextView.text = "Description"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow  = layoutInflater.inflate(R.layout.contentlist_item,parent,false)
        return  ContentViewHolder(cellforRow)
    }

    override fun getItemCount(): Int {
        return 8
    }


}

class ContentViewHolder(val view:View): RecyclerView.ViewHolder(view) {

}