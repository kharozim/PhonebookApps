package com.kharozim.phonebookapps.remote.model

import com.google.gson.annotations.SerializedName

data class GetAllContactModel(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("imageName")
	val imageName: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("job")
	val job: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)