package com.example.phase1proj.views


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.adapter.ParentRecyclerViewAdapter
import com.example.phase1proj.model.Category
import com.example.phase1proj.model.Item
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout.*


class layout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mysearchview: androidx.appcompat.widget.SearchView

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.layout)


        recyclerView = recyclerParent
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@layout,
                RecyclerView.VERTICAL,
                false
            )
            adapter = ParentRecyclerViewAdapter(
                getParents(40, "")
            )
        }
        mysearchview = findViewById(R.id.searchText)

        var noResultsText = findViewById<TextView>(R.id.noResults)
        var noResultsImage = findViewById<ImageView>(R.id.noresultsimage)
        mysearchview.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@layout, "Looking for $query", Toast.LENGTH_LONG).show()

                mysearchview.clearFocus()
                var categories = getParents(40, query)
                if (categories.isNotEmpty()) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            this@layout,
                            RecyclerView.VERTICAL,
                            false
                        )
                        adapter =
                            ParentRecyclerViewAdapter(
                                categories
                            )
                        invalidate()

                    }
                    noResultsText.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                    noResultsImage.visibility = View.INVISIBLE

                } else {
                    noResultsText.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    noResultsImage.visibility = View.VISIBLE
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    mysearchview.clearFocus()
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            this@layout,
                            RecyclerView.VERTICAL,
                            false
                        )
                        adapter =
                            ParentRecyclerViewAdapter(
                                getParents(40, "")
                            )
                        invalidate()
                        recyclerView.visibility = View.VISIBLE
                        noResultsText.visibility = View.INVISIBLE
                        noResultsImage.visibility = View.INVISIBLE
                    }
                    return false
                } else {
                    recyclerView.visibility = View.INVISIBLE

                }


                return false
            }
        })

    }


    private fun getParents(count: Int, searchText: String?): List<Category> {
        var parents = mutableListOf<Category>()
        repeat(count / 2) {
            val parent =
                Category("Veggies", getChildren(20))
            parents.add(parent)
        }
        repeat(count / 2) {
            val parent =
                Category("Fruits", getChildren(20))
            parents.add(parent)
        }
        if (searchText != "") {


            for (i in parents) {

                if (!i.name.contains(searchText.toString(), ignoreCase = true)) {

                    i.children = i.children.filter {
                        it.name.contains(
                            searchText.toString(),
                            ignoreCase = true
                        )
                    }.toMutableList()
                }
                println(i.children.size)
            }
            parents = parents.filter { it.children.isNotEmpty() }.toMutableList()
        }
        return parents

    }


    private fun getChildren(count: Int): MutableList<Item> {
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

    fun onClickStore(v: View?) {

        val tv = findViewById(R.id.categoryName) as TextView


        //alter text of textview widget
        tv.text = "This text view is clicked"
        //assign the textview forecolor
        tv.setTextColor(Color.BLUE)
    }


}