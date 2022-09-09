package com.example.testcleanapplication.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testcleanapplication.databinding.RowBinding
import com.example.testcleanapplication.domain.model.TodosModel
import javax.inject.Inject

class TodoAdapter @Inject constructor(): ListAdapter<TodosModel, TodosViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {

        val model = getItem(position)
        holder.bind(getItem(position)!!)
        holder.setOnListHolderListener(listener, model)
    }



    class MyDiffUtil: DiffUtil.ItemCallback<TodosModel>() {
        override fun areItemsTheSame(oldItem: TodosModel, newItem: TodosModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosModel, newItem: TodosModel): Boolean {
            return oldItem.id == newItem.id
        }

    }



    private var listener: TodosItemInteraction? = null
    fun setListener(listener: TodosItemInteraction) {
        this.listener = listener
    }

}