package com.example.testcleanapplication.controllers

import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanapplication.databinding.RowBinding
import com.example.testcleanapplication.domain.model.TodosModel



class TodosViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TodosModel) {

        binding.txtTitle.text = item.title
        binding.txtUserId.text = item.userId.toString()
    }

    fun setOnListHolderListener(listener: TodosItemInteraction?, model: TodosModel?) {

        binding.root.setOnClickListener {

            listener?.itemClicked(model)
        }
    }

}