package com.project.phr.model

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone

data class User (val name:String="",val email:String="",val phone:String?="")