package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName
import com.kharozim.phonebookapps.models.ModelGetAllContact

data class ResponseGetAllContact(

    @field:SerializedName("data")
    val data: List<ModelGetAllContact>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean

)