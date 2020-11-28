package com.kharozim.phonebookapps.remote.body

import com.google.gson.annotations.SerializedName

data class BodySignIn(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)