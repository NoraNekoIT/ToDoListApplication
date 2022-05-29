package com.noranekoit.submission.todolistapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.noranekoit.submission.todolistapplication.R
import com.noranekoit.submission.todolistapplication.data.ToDo

class MainActivity : AppCompatActivity() {
    private lateinit var rvToDo: RecyclerView
    private val listDummyNote = ArrayList<ToDo>()
    private val listTodoAdapter = ListTodoAdapter(listDummyNote)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvToDo = findViewById(R.id.rv_todo)
        rvToDo.setHasFixedSize(true)

        initialDataList()
        showReyclerList()
        showDialogInput()
    }

    //    set data
    private fun initialDataList() {
        listDummyNote.add(
            0,
            ToDo("todo kesatu")
        )
    }

    // input item
    private fun checkLastIndexForInput(pesan: String) {
        val check: Int = listDummyNote.lastIndex
//        listDummyNote.add(
//            check + 1,
//            ToDo(pesan)
//        )
        listTodoAdapter.addItem(check+1 , ToDo(pesan))

    }

    //    nampilkan data dari recycler
    private fun showReyclerList() {
        rvToDo.layoutManager = LinearLayoutManager(this)

        val swipeGestures = object : SwipeGestures(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        listTodoAdapter.deleteItem(viewHolder.adapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGestures)
        touchHelper.attachToRecyclerView(rvToDo)

        rvToDo.adapter = listTodoAdapter
        listTodoAdapter.setOnItemClickListener(object  : ListTodoAdapter.OnItemClickListeners {
            @SuppressLint("CheckResult")
            override fun onItemClick(position: Int) {
                MaterialDialog(this@MainActivity).show {
                    input(hint = "input untuk update todo") { _, textTodoInput ->
                        listTodoAdapter.updateItem(position,textTodoInput.toString())
                    }
                    title(text = "todo")
                    positiveButton(text = "simpan")
                    negativeButton(text = "batal")
                }
//                Toast.makeText(this@MainActivity,"test $position",Toast.LENGTH_SHORT)
//                    .show()
            }

        })
    }

    //    show dialog input
    @SuppressLint("CheckResult")
    private fun showDialogInput() {
        val fab: View = findViewById(R.id.fab_add)
        fab.setOnClickListener {
            MaterialDialog(this).show {
                input(hint = "input todo") { _, textTodoInput ->

                    checkLastIndexForInput(textTodoInput.toString())
                }
                title(text = "todo")
                positiveButton(text = "simpan")
                negativeButton(text = "batal")
            }
        }
    }

}