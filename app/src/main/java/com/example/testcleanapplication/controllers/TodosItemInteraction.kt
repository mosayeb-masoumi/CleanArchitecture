package com.example.testcleanapplication.controllers

import com.example.testcleanapplication.domain.model.TodosModel

interface TodosItemInteraction {
     fun itemClicked(model: TodosModel?)
}