package com.example.listviewkotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val mList: List<UserData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.textView1)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
       // holder.imageView.setImageResource(ItemsViewModel.img)

        // sets the text to the textview from our itemHolder class
       // holder..text = ItemsViewModel.title
        holder.textView.text = ItemsViewModel.content

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
      //  val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.textView1)
    }
}
