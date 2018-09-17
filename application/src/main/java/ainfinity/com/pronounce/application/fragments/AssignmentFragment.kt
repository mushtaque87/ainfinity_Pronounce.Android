package ainfinity.com.pronounce.application.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.ContentViewAdapter
import kotlinx.android.synthetic.main.fragment_assignment.*
import kotlinx.android.synthetic.main.fragment_content.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AssignmentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_assignment, container, false)
        //HTTPManager.getContentGroup(0,TokenManager.instance.usedid!!,"false")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        assignment_RecyclerView.layoutManager = LinearLayoutManager(this.context)
        assignment_RecyclerView.adapter  = ContentViewAdapter()
    }


}
