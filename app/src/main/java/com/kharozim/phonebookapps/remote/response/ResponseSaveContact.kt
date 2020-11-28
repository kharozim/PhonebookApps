package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName
import com.kharozim.phonebookapps.models.ModelGetAllContact

data class ResponseSaveContact(

    @field:SerializedName("data")
    val data: ModelGetAllContact,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
)