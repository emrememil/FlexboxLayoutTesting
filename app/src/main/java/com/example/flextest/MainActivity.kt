package com.example.flextest

import android.animation.LayoutTransition
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_field.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: FlexboxLayoutManager
    private lateinit var adapter: SelectedRecyclerViewAdapter
    private lateinit var chooser: ColumnChooser
    //private lateinit var viewModel: ManagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        adapter.setOnItemClickListener { data, view ->
            view.img_cancel.setOnClickListener {
                //viewModel.removeSelectedData(it, adapter)
                chooser.removeData(data)
                adapter.setData(chooser.getSelectedData())
                recyclerView.adapter = adapter
            }
        }

        fab.setOnClickListener {
            val dialog = AddDataDialog(chooser, adapter, recyclerView)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(dialog, TAG)
            ft.commitAllowingStateLoss()
        }

//        viewModel.mutableSelectedDataList.observe(this, Observer {
//            if (it.size == 0){
//                textViewDataNotFound.visibility = View.VISIBLE
//            }else textViewDataNotFound.visibility = View.GONE
//
//            adapter.setData(it)
//            recyclerView.adapter = adapter
//        })

        val itemTouchHelperCallback = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags, 0)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
    private fun setDataList(): ArrayList<Data> {
        val dataList = ArrayList<Data>()
        dataList.add(Fields.DATE)
        dataList.add(Fields.BARCODE)
        dataList.add(Fields.DESCRIPTION)
        dataList.add(Fields.DROPDOWN)
        dataList.add(Fields.INTEGER)
        dataList.add(Fields.MULTICHOICE)
        dataList.add(Fields.NAME)
        dataList.add(Fields.NUMERIC)
        dataList.add(Fields.RADIO)
        dataList.add(Fields.RANDOM)
        dataList.add(Fields.SWITCH)
        dataList.add(Fields.TIME)
        dataList.add(Fields.YESNO)
        return dataList
    }

    private fun init() {
        chooser = ColumnChooser(this)
        chooser.setData(setDataList())
        // viewModel = ViewModelProviders.of(this).get(ManagerViewModel::class.java)
        //viewModel.fillUnselectedDataList()
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        recyclerView.layoutManager = layoutManager

        adapter = SelectedRecyclerViewAdapter()
        recyclerView.adapter = adapter
        linearContainer.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

    }


    companion object {
        const val TAG = "MainActivity"
    }
}
