package com.example.phase1proj.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.adapter.SpecificCategoryListAdapter
import com.example.phase1proj.model.Item
import kotlinx.android.synthetic.main.categorylist.*

class CategoryListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

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
                getCategoryList(categoryName, 40)
            )
        }
    }

    private fun getCategoryList(categoryName: String, count: Int): List<Item> {

        val children = mutableListOf<Item>()
        repeat(count / 2) {
            children.add(
                Item(
                    "Carrot",
                    "Veggies",
                    R.drawable.veggie1,
                    "New Veggie"
                )
            )
        }
        repeat(count / 2) {
            children.add(
                Item(
                    "Apple",
                    "Fruits",
                    R.drawable.veggie2,
                    "New Veggie"
                )
            )
        }
        return children

    }
}
