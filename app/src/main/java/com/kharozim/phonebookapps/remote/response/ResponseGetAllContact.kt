package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName
import com.kharozim.phonebookapps.remote.model.GetAllContactModel
import com.kharozim.phonebookapps.remote.model.SignInModel

data class ResponseGetAllContact(

    @field:SerializedName("data")
    val data: List<GetAllContactModel>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean

)