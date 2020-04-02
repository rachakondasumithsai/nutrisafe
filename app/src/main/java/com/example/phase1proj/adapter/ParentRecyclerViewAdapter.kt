package com.example.phase1proj.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.model.Category
import com.example.phase1proj.views.activity.CategoryListActivity
import kotlinx.android.synthetic.main.parent_view_list.view.*

// This is a class which stores the list of all categories with items in each category using
// a recycler view
class ParentRecyclerViewAdapter(
    private val parentList: List<Category>
) :
    RecyclerView.Adapter<ParentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val layoutInflater = LayoutInflater.from(parent.context)

        // Add the view to be called and inflated for the adapter class
        view = layoutInflater.inflate(R.layout.parent_view_list, parent, false)

        // return that view
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = parentList[position]

        // Set an on click listener on the category name which will show a small message
        // using a Toast
        holder.textLayout.setOnClickListener {
            openActivity(holder)
            Toast.makeText(holder.textLayout.context, holder.textView.text, Toast.LENGTH_LONG)
                .show()
        }
        holder.textView.text = parentList[position].name

        // Add a recycler view to the holder which stores the view to be shown
        holder.recyclerView.apply {
            layoutManager = GridLayoutManager(
                holder.recyclerView.context,
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )
            // Attaches the child recycler view to the parent adapter
            adapter =
                ChildRecyclerViewAdapter(parent.children)
        }
    }

    // Function to open the activity when the user clicks on an activity
    private fun openActivity(holder: ViewHolder) {
        // Intent is a description of an operation that is being performed like opening a new
        // activity, or a fragment.
        val intent = Intent(
            holder.textView.context,
            CategoryListActivity::class.java
        )

        // Adding extra information to the intent which can be retrieved in the new activity
        intent.putExtra("categoryName", holder.textView.text)

        // Starts a new activity using the intent which we created with no additional options
        startActivity(holder.textView.context, intent, null)
    }

    // Function to return the number of different categories present
    override fun getItemCount(): Int {
        return parentList.size
    }

    // Required for the data to be shown in the xml
    inner class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var recyclerView: RecyclerView = itemsView.recyclerChild
        var textView: TextView = itemsView.categoryName
        var textLayout: ConstraintLayout = itemsView.textLayout

    }

}