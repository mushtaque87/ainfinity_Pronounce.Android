package ainfinity.com.pronounce.application.delegates

import ainfinity.com.pronounce.application.adapters.CrumActionType
import ainfinity.com.pronounce.application.datamodels.UserManagement.ContentGroup

interface ContentActivityDelegates {

    fun  fetchContentGroup(content: ContentGroup, actionType:CrumActionType)  : Unit

}


interface SettingActivityDelegates {

    fun logOut() : Unit

}