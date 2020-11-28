package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGetDelete(

	@field:SerializedName("data")
	val data: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)