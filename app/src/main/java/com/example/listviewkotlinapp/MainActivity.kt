package com.example.listviewkotlinapp


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    // ArrayList of class ItemsViewModel
    var data = ArrayList<UserData>()

    // This will pass the ArrayList to our Adapter
    val adapter = MyAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.my_recycler_view)
        val button = findViewById<FloatingActionButton>(R.id.add_btn)

        button.setOnClickListener {
            addInfo()
        }

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..52) {
            data.add(UserData(i, "Item $i")).also { data.sortBy { it.content } }
        }

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                data.removeAt(pos)
                adapter.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.activity_edit_layout, null)
        val content = v.findViewById<EditText>(R.id.content_edit)
        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("OK") { dialog, _ ->
            val value = content.text.toString()
            data.add(UserData(value.length, " $value"))
            data.sortBy { it.content }
            adapter.setDatas(data)
            // adapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding User Content Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}
