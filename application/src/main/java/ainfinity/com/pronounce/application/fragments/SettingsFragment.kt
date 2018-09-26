package ainfinity.com.pronounce.application.fragments



import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.adapters.SettingsViewAdapter
import kotlinx.android.synthetic.main.fragment_settings.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.HORIZONTAL
import ainfinity.com.pronounce.application.delegates.SettingActivityDelegates
import ainfinity.com.pronounce.application.helpers.Helper
import ainfinity.com.pronounce.application.activities.MainActivity
import ainfinity.com.pronounce.application.activities.TabBarActivity
import ainfinity.com.pronounce.application.helpers.AppSettings


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment(), SettingActivityDelegates {
    var settingAdapter  = SettingsViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)



        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        settings_RecyclerView.layoutManager = LinearLayoutManager(this.context)
        settings_RecyclerView.addItemDecoration(DividerItemDecoration(settings_RecyclerView.getContext(), DividerItemDecoration.VERTICAL))
        settings_RecyclerView.adapter  = settingAdapter
        settingAdapter.delegate = this

        //Add Divider to recyclerView
        val dividerDecor = DividerItemDecoration(settings_RecyclerView.context, HORIZONTAL)
        settings_RecyclerView.addItemDecoration(dividerDecor)


        /*
        val sections = ArrayList()

        //Sections
        sections.add(SectionedRecyclerViewAdapter.Section(0, "Weekends"))
        sections.add(SectionedRecyclerViewAdapter.Section(2, "Favorites"))


        //Add your adapter to the sectionAdapter
        val dummy = arrayOfNulls<SectionedRecyclerViewAdapter.Section>(sections.size)
        val mSectionedAdapter = SectionedRecyclerViewAdapter(this, R.layout.item_section, R.id.sectionName, adapter)
        mSectionedAdapter.setSections(sections.toTypedArray())

        //Apply this adapter to the RecyclerView
        settings_RecyclerView.setAdapter(mSectionedAdapter)
        */
    }

    override fun onStart() {
        super.onStart()
        //fetchContentGroup()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun logOut() {
        Helper.printLogs("logOut")
        (activity as TabBarActivity).showLoginActivity()

       // (context as MainActivity).


        // val mainIntent =Intent()
       // mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
       // startActivity(mainIntent)


    }

/*
    fun showSettingSelectionScreen(settingType : SettingType) {
        let settingSelectionViewController =     SettingSelectionTableViewController(nibName: "SettingSelectionTableViewController", bundle: nil)
        settingSelectionViewController.settingtype = settingType
        self.navigationController?.pushViewController(settingSelectionViewController, animated: true)
    }
*/

}
