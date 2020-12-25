package com.example.flextest

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_data_bottom_sheet_dialog.*

class AddDataDialog(
    chooser: ColumnChooser,
    adapter2: SelectedRecyclerViewAdapter,
    recyclerView2: RecyclerView
) : BottomSheetDialogFragment() {

    private lateinit var layoutManager: FlexboxLayoutManager
    private lateinit var adapter: UnselectedDataRecyclerViewAdapter
    private var columnChooser = chooser
    private var mainAdapter = adapter2
    private var mainRecyclerView = recyclerView2

    //private lateinit var viewModel: ManagerViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper = ContextThemeWrapper(activity, theme)
        return inflater.cloneInContext(contextThemeWrapper)
            .inflate(R.layout.add_data_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(requireActivity()).get(ManagerViewModel::class.java)

        layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        addDataRecyclerView.layoutManager = layoutManager
        adapter = UnselectedDataRecyclerViewAdapter()
        //adapter.setData(viewModel.mutableUnselectedDataList.value)
        adapter.setData(columnChooser.getUnselectedData())
        addDataRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            //viewModel.addSelectedData(it,adapter)
            columnChooser.addData(it)
            mainAdapter.setData(columnChooser.getSelectedData())
            mainRecyclerView.adapter = mainAdapter
            addDataRecyclerView.adapter = adapter
        }
    }

    companion object {
        const val TAG = "AddDataDialog"
    }
}