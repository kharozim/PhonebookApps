package com.kharozim.phonebookapps.remote.response

import com.google.gson.annotations.SerializedName
import com.kharozim.phonebookapps.remote.model.SignInModel

data class ResponseSignIn(

	@field:SerializedName("data")
	val data: SignInModel,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)