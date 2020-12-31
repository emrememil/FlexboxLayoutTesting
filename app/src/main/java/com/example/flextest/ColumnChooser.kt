package com.example.flextest


import android.content.Context
import android.view.View

class ColumnChooser(context: Context) : View(context) {

    private val viewModel = ManagerViewModel()

    fun setData(data: ArrayList<Data>) {
        viewModel.setData(data)
    }

    fun addToSelectedData(data: Data){
        viewModel.addSelectedData(data)
    }

    fun getSelectedData(): ArrayList<Data>?{
        return viewModel.getSelectedData()
    }

    fun getUnselectedData(): ArrayList<Data>?{
        return viewModel.getUnselectedData()
    }


    fun removeData(data: Data){
        viewModel.removeData(data)
    }

}