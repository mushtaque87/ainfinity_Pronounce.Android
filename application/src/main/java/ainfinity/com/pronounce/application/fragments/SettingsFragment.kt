package ainfinity.com.pronounce.application.fragments



import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.ContentViewAdapter
import ainfinity.com.pronounce.application.adapters.SettingsViewAdapter
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_settings.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.HORIZONTAL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        settings_RecyclerView.layoutManager = LinearLayoutManager(this.context)
        settings_RecyclerView.adapter  = SettingsViewAdapter()


        //Add Divider to recyclerView
        val dividerDecor = DividerItemDecoration(settings_RecyclerView.context, HORIZONTAL)
        settings_RecyclerView.addItemDecoration(dividerDecor)
    }


}
