package com.kharozim.phonebookapps.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharozim.phonebookapps.databinding.ItemListContactBinding
import com.kharozim.phonebookapps.models.ModelGetAllContact

class ContactAdapter(private val context: Context) :
    RecyclerView.Adapter<ContactAdapter.Viewholder>() {

    var listContact = mutableListOf<ModelGetAllContact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Viewholder(private val binding: ItemListContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(modelGetAllContact: ModelGetAllContact) {
            binding.run {
                tvName.text = modelGetAllContact.name
                tvPhone.text = modelGetAllContact.phone
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
    return Viewholder(ItemListContactBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binData(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size
}