package com.example.phase1proj.views

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.adapter.SpecificCategoryListAdapter
import com.example.phase1proj.model.Category
import com.example.phase1proj.model.Item
import com.google.gson.Gson
import kotlinx.android.synthetic.main.categorylist.*

// This is the recycler view for the list of items under each category
// of nutrients like fruits, vegetables, etc
class CategoryListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val gson = Gson()
    val context: Context = this@CategoryListActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorylist)

        // Get the category name from the activity using which this view is called
        val categoryName = intent.getStringExtra("categoryName")

        // Create a recycler view using the category name which gives a list of all items
        // present in each category. This is done by assigning a list to the adapter
        recyclerView = categoryListRecycler

        // The layout for the recycler view is a grid layout with two items in each row
        // and the layout is vertically recycled.
        recyclerView.apply {
            layoutManager = GridLayoutManager(
                this@CategoryListActivity,
                2,
                GridLayoutManager.VERTICAL,
                false
            )

            // Call the adapter which has to be hit using the data from the category items for which
            // a view with list of items has to be created
            adapter = SpecificCategoryListAdapter(
                getCategoryList(categoryName)
            )
        }
    }

    // A private function to get the the items present in each category by parsing the
    // category.json file using google's gson object.
    private fun getCategoryList(categoryName: String): List<Item> {

        // First read the category.json as a string.
        val text = context?.resources?.openRawResource(R.raw.category)
            ?.bufferedReader()
            ?.use { it?.readText() }

        // Convert the json string to the list using the gson object
        var categoryList = gson.fromJson(text, Array<Category>::class.java)

        // Filter the categoryList with the name that is sent by the previous activity
        var categoryItems = categoryList.single() {
            it.name.toLowerCase().contains(
                categoryName.toLowerCase()
            )
        }

        // Return that category with its children to the adapter
        return categoryItems.children
    }
}
