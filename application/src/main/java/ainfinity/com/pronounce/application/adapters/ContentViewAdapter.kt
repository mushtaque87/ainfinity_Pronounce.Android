package ainfinity.com.pronounce.application.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import  android.view.View
import android.view.ViewGroup
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.datamodels.UserManagement.ContentGroup
import ainfinity.com.pronounce.application.delegates.ContentActivityDelegates
import ainfinity.com.pronounce.application.extensions.dateFromEpoc
import ainfinity.com.pronounce.application.extensions.toText
import ainfinity.com.pronounce.application.helpers.Helper
import kotlinx.android.synthetic.main.contentlist_item.view.*
import java.util.*


enum class  ContentType {
    CONTENT , ASSIGNMENT
}

enum class  CrumActionType {
    APPEND , REMOVE
}


class ContentViewAdapter: RecyclerView.Adapter<ContentViewHolder>() {
     var contentList = ArrayList<ContentGroup>()
     var delegate : ContentActivityDelegates? = null
     var contentType : ContentType = ContentType.CONTENT

    public fun ContentViewAdapter(delegate:ContentActivityDelegates){
        this.delegate =  delegate
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = contentList[position]

        holder.view.titleTextView.text =  content.name
         holder.view.createdDateTextView.text = Date().dateFromEpoc(content.creation_date).toText("EEE, d MMM, yyyy")
        //holder.view.submissionDateTextView.text = content.
        holder.view.descriptionTextView.text = content.description
        holder.view.setOnClickListener { getContent(content) }
        if (contentType.equals(ContentType.CONTENT)){
            holder.view.submissionDateTextView.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow  = layoutInflater.inflate(R.layout.contentlist_item,parent,false)

        return  ContentViewHolder(cellforRow)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    fun refreshRecyclerView(){
        notifyDataSetChanged()
    }

    fun getContent(content: ContentGroup){
        delegate?.fetchContentGroup(content,CrumActionType.APPEND)
    }

}

class ContentViewHolder(val view:View): RecyclerView.ViewHolder(view) , View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    //4
    override fun onClick(v: View) {
        Helper.printLogs("Row Clicked")
    }

}