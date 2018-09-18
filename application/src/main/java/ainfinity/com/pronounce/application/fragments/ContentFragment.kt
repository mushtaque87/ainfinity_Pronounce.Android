package ainfinity.com.pronounce.application.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ainfinity.com.pronounce.application.servicemanager.ServiceManager
import ainfinity.com.pronounce.application.servicemanager.TokenManager
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.ContentViewAdapter
import kotlinx.android.synthetic.main.fragment_content.*
import ainfinity.com.pronounce.application.delegates.ContentActivityDelegates
import ainfinity.com.pronounce.application.helpers.AppSettings
import android.graphics.Color.parseColor
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import android.graphics.Color
import android.support.v7.widget.DividerItemDecoration




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ContentFragment  : Fragment() ,   ContentActivityDelegates {
    var contentAdapter  = ContentViewAdapter()

    public fun ContentFragment() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment

        //val rootView = inflater.inflate(R.layout.fragment_content,container,false)
        //content_recyclerView = rootView.findViewById(R.id.content_recyclerView) as RecyclerView
        //contentAdapter = ContentViewAdapter()
        contentAdapter.delegate = this

        return inflater.inflate(R.layout.fragment_content, container, false)

    }

    override fun onStart() {
        super.onStart()
        fetchContentGroup()

    }

    override fun onResume() {
        super.onResume()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        content_recyclerView.layoutManager = LinearLayoutManager(this.context)
        content_recyclerView.addItemDecoration(DividerItemDecoration(content_recyclerView.getContext(), DividerItemDecoration.VERTICAL))
        content_recyclerView.adapter  = contentAdapter
    }

    override fun fetchContentGroup(id : Int) : Unit {

        val progressDialog = ACProgressFlower.Builder(activity)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Fetching Contents. Please wait")
                .fadeColor(Color.DKGRAY)
                .isTextExpandWidth(true).build()
        progressDialog.show()

        ServiceManager.getContentGroup(id,TokenManager.instance.usedid!!,"false" ,{
            println(it)
            progressDialog.dismiss()
            contentAdapter.contentList.clear()
            contentAdapter.contentList.addAll(it)
            contentAdapter.notifyDataSetChanged()

            //content_recyclerView.adapter.notifyDataSetChanged()
        },{
            progressDialog.dismiss()
            print(it)
        })
    }


}
