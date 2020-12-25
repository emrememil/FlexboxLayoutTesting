package com.example.flextest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManagerViewModel: ViewModel() {

    var mutableSelectedDataList = MutableLiveData<ArrayList<Data>>()
    var mutableUnselectedDataList = MutableLiveData<ArrayList<Data>>()
    private var selectedDataList = ArrayList<Data>()
    private var unselectedDataList = ArrayList<Data>()

    fun addSelectedData(
        data: Data,
        adapter: UnselectedDataRecyclerViewAdapter
    ){
        mutableUnselectedDataList.value?.let {
            it.remove(data)
            adapter.setData(it)
        }
        selectedDataList.add(data)
        mutableSelectedDataList.value = selectedDataList

        unselectedDataList.remove(data)
        mutableUnselectedDataList.value = unselectedDataList

    }

    fun removeSelectedData(data: Data, adapter: SelectedRecyclerViewAdapter){
        mutableSelectedDataList.value?.let {
            it.remove(data)
            adapter.setData(it)
        }
        selectedDataList.remove(data)
        mutableSelectedDataList.value = selectedDataList

        unselectedDataList.add(data)
        mutableUnselectedDataList.value = unselectedDataList
    }

    fun fillUnselectedDataList() {
        unselectedDataList.clear()
        unselectedDataList.add(Fields.DATE)
        unselectedDataList.add(Fields.BARCODE)
        unselectedDataList.add(Fields.DESCRIPTION)
        unselectedDataList.add(Fields.DROPDOWN)
        unselectedDataList.add(Fields.INTEGER)
        unselectedDataList.add(Fields.MULTICHOICE)
        unselectedDataList.add(Fields.NAME)
        unselectedDataList.add(Fields.NUMERIC)
        unselectedDataList.add(Fields.RADIO)
        unselectedDataList.add(Fields.RANDOM)
        unselectedDataList.add(Fields.SWITCH)
        unselectedDataList.add(Fields.TIME)
        unselectedDataList.add(Fields.YESNO)
        mutableUnselectedDataList.value = unselectedDataList
    }

    fun setData(data: ArrayList<Data>) {
        mutableUnselectedDataList.value?.clear()
        unselectedDataList = data
        mutableUnselectedDataList.value = unselectedDataList
    }

    fun getSelectedData() : ArrayList<Data>?{
        return mutableSelectedDataList.value
    }

    fun getUnselectedData() : ArrayList<Data>?{
        return mutableUnselectedDataList.value
    }

    fun addData(data: Data){
        selectedDataList.add(data)
        unselectedDataList.remove(data)
        mutableSelectedDataList.value = selectedDataList
        mutableUnselectedDataList.value = unselectedDataList
    }

    fun removeData(data: Data){
        selectedDataList.remove(data)
        unselectedDataList.add(data)
        mutableUnselectedDataList.value = unselectedDataList
        mutableSelectedDataList.value = selectedDataList
    }


    companion object {
        const val TAG = "ManagerViewModel"
    }
}