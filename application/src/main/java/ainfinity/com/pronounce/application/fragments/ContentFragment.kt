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
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import moe.feng.common.view.breadcrumbs.BreadcrumbsView
import moe.feng.common.view.breadcrumbs.DefaultBreadcrumbsCallback
import moe.feng.common.view.breadcrumbs.model.BreadcrumbItem
import java.util.*
import ainfinity.com.pronounce.application.datamodels.UserManagement.ContentGroup
import ainfinity.com.pronounce.application.adapters.CrumActionType
import ainfinity.com.pronounce.application.helpers.Helper
import kotlinx.android.synthetic.main.fragment_content.breadcrumbs_view


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 *
 */


class ContentFragment  : Fragment() ,   ContentActivityDelegates {
    private var mBreadcrumbsView: BreadcrumbsView? = null
    var contentAdapter  = ContentViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment

//        val rootView = inflater.inflate(R.layout.fragment_content,container,false)
//        content_recyclerView = rootView.findViewById(R.id.content_recyclerView) as RecyclerView
//        contentAdapter = ContentViewAdapter()


        return inflater.inflate(R.layout.fragment_content, container, false)

    }

    override fun onStart() {
        super.onStart()
        mBreadcrumbsView = view?.findViewById(R.id.breadcrumbs_view) as BreadcrumbsView;
        mBreadcrumbsView?.setItems(ArrayList(Arrays.asList(
                BreadcrumbItem.createSimpleItem("Root Path")
        )))
        mBreadcrumbsView?.setCallback(object : DefaultBreadcrumbsCallback<BreadcrumbItem>() {
            override fun onNavigateBack(item: BreadcrumbItem, position: Int) {

            }

            override fun onNavigateNewLocation(newItem: BreadcrumbItem, changedPosition: Int) {

            }
        })

        fetchContentGroup(0)

    }

    override fun onResume() {
        super.onResume()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        content_recyclerView.layoutManager = LinearLayoutManager(this.context)
        content_recyclerView.addItemDecoration(DividerItemDecoration(content_recyclerView.getContext(), DividerItemDecoration.VERTICAL))
        content_recyclerView.adapter  = contentAdapter
        contentAdapter.delegate = this
    }

    override fun fetchContentGroup(content:ContentGroup ,  actionType:CrumActionType) : Unit {

        if (content.has_units == false ) {
            fetchContentGroup(content.id)
//            when(actionType) {
//                APPEND ->
//                    REMOVE ->
//            }

            mBreadcrumbsView?.addItem(createItem(content.name))

        }
        else {
            Helper.printLogs("Show Unit Screen")
        }
    }

    fun fetchContentGroup(id : Int ) : Unit {

        val progressDialog = ACProgressFlower.Builder(activity)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Fetching Contents. Please wait")
                .fadeColor(Color.DKGRAY)
                .isTextExpandWidth(true).build()
        progressDialog.show()

        ServiceManager.getContentGroup(id, TokenManager.instance.usedid!!, "false", {
            println(it)
            progressDialog.dismiss()
            contentAdapter.contentList.clear()
            contentAdapter.contentList.addAll(it)
            contentAdapter.notifyDataSetChanged()

            //content_recyclerView.adapter.notifyDataSetChanged()
        }, {
            progressDialog.dismiss()
            print(it)
        })

    }
    private fun createItem(title: String): BreadcrumbItem {
        val list = ArrayList<String>()
        list.add(title)
//        list.add("$title A")
//        list.add("$title B")
//        list.add("$title C")
        return BreadcrumbItem(list)
    }


}
