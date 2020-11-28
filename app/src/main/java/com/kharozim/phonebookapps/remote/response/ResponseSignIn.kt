package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName
import com.kharozim.phonebookapps.models.ModelSignIn

data class ResponseSignIn(

    @field:SerializedName("data")
	val data: ModelSignIn,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Boolean
)