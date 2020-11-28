package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseSignUp(

	@field:SerializedName("data")
	val data: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)