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

class CategoryListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val gson = Gson()
    val context: Context = this@CategoryListActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorylist)
        val categoryName = intent.getStringExtra("categoryName")
        recyclerView = categoryListRecycler
        recyclerView.apply {
            layoutManager = GridLayoutManager(
                this@CategoryListActivity,
                2,
                GridLayoutManager.VERTICAL,
                false
            ) as RecyclerView.LayoutManager?
            adapter = SpecificCategoryListAdapter(
                getCategoryList(categoryName)
            )
        }
    }

    private fun getCategoryList(categoryName: String): List<Item> {

        val text = context?.resources?.openRawResource(R.raw.category)
            ?.bufferedReader()
            ?.use { it?.readText() }

        // Convert the json string to the list using the gson object
        var categoryList = gson.fromJson(text, Array<Category>::class.java)

        var categoryItems = categoryList.single() {
            it.name.toLowerCase().contains(
                categoryName.toLowerCase()
            )
        }

        return categoryItems.children
    }
}
