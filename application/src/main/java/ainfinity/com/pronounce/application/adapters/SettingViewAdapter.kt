package ainfinity.com.pronounce.application.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import ainfinity.com.pronounce.application.R
import ainfinity.com.pronounce.application.delegates.SettingActivityDelegates
import kotlinx.android.synthetic.main.logout_item.view.*
import kotlinx.android.synthetic.main.settinglist_item.view.*





enum class  SettingType {
    ACCOUNT ,
    LANGUAGE ,
    THEME,
    GRAPHTYPE,
    LOGOUT
}


class SettingsViewAdapter: RecyclerView.Adapter<SettingsViewHolder>() {

    companion object {
        const val ACCOUNT = 0
        const val LANGUAGE = 1
        const val THEME = 2
        const val GRAPHTYPE = 3
        const val LOGOUT = 4
    }

    val settingType : SettingType? = null
    val settingTypes = arrayListOf<String>("Accounts","Language","Theme","Graph Type","Logout")
    var delegate : SettingActivityDelegates? = null

    public fun SettingsViewAdapter(delegate: SettingActivityDelegates){
        this.delegate =  delegate
    }


    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
       when (position) {
            ACCOUNT,LANGUAGE,THEME,GRAPHTYPE -> {
                holder.view.settingsTextView.text = settingTypes[position]
            }
            else -> {
                holder.view.logoutTextView.text =  "Logout"
                holder.view.setOnClickListener { delegate?.logOut() }

            }
        }
        //holder.view.setOnClickListener {  }
    }

    fun SettingsViewHolder(view: View,onItemSelectedListener: AdapterView.OnItemSelectedListener) {
        println("onItemSelectedListener")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        var cell : View

            when (viewType) {
            ACCOUNT,LANGUAGE,THEME,GRAPHTYPE -> {
                 val cell = layoutInflater.inflate(R.layout.settinglist_item, parent, false)
                cell.minimumHeight = 80
                 return  SettingsViewHolder(cell)
             }
            else -> {
                val cell = layoutInflater.inflate(R.layout.logout_item, parent, false)
                cell.minimumHeight = 100
                return  SettingsViewHolder(cell)
            }
            //val cell = layoutInflater.inflate(R.layout.settinglist_item, parent, false)

            //val layoutInflater = LayoutInflater.from(parent?.context)
        }

        return  SettingsViewHolder(cell)
    }


    override fun getItemCount(): Int {
        return settingTypes.count()
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            ACCOUNT -> {
                return ACCOUNT
            }
            LANGUAGE -> {
                return ACCOUNT
            }
            THEME -> {
                return THEME
            }
            GRAPHTYPE -> {
                return GRAPHTYPE
            }
            LOGOUT -> {
                return LOGOUT
            }
        }
        return  -1
    }


}

class SettingsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}