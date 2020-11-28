package com.kharozim.phonebookapps.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharozim.phonebookapps.databinding.ItemListContactBinding
import com.kharozim.phonebookapps.models.ModelGetAllContact

class ContactAdapter(private val context: Context, private val listener: ListenerContact) :
    RecyclerView.Adapter<ContactAdapter.Viewholder>() {

    var listContact = mutableListOf<ModelGetAllContact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun deleteContactById(id: Int) {
        val index = listContact.indexOfFirst { it.id == id }
        if (index != -1){
            listContact = listContact.filter { it.id != id }.toMutableList()
            notifyItemRemoved(index)
        }
    }

    inner class Viewholder(
        private val binding: ItemListContactBinding,
        private val listener: ListenerContact
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(modelGetAllContact: ModelGetAllContact) {
            binding.run {
                tvName.text = modelGetAllContact.name
                tvPhone.text = modelGetAllContact.phone
                btDel.setOnClickListener { listener.onDelete(modelGetAllContact.id) }

            }
        }

    }

    interface ListenerContact {
        fun onDelete(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemListContactBinding.inflate(LayoutInflater.from(context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binData(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size
}