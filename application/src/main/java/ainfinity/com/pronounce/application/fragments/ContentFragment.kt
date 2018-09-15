package ainfinity.com.pronounce.application.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ainfinity.com.pronounce.application.servicemanager.HTTPManager
import ainfinity.com.pronounce.application.servicemanager.TokenManager
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.ContentViewAdapter
import kotlinx.android.synthetic.main.fragment_content.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //val rootView = inflater.inflate(R.layout.fragment_content,container,false)
        //content_recyclerView = rootView.findViewById(R.id.content_recyclerView) as RecyclerView

        return inflater.inflate(R.layout.fragment_content, container, false)

    }

    override fun onStart() {
        super.onStart()
        HTTPManager.getContentGroup(0,TokenManager.instance.usedid!!,"false" ,{
            println(it)
        },{

            print(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        content_recyclerView.layoutManager = LinearLayoutManager(this.context)
        content_recyclerView.adapter  = ContentViewAdapter()
    }



}
