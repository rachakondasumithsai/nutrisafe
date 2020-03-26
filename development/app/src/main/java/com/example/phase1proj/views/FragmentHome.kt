package com.example.phase1proj.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.adapter.ParentRecyclerViewAdapter
import com.example.phase1proj.model.Category
import com.example.phase1proj.model.Item
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout.*

class FragmentHome : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchview: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerParent) as RecyclerView

        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = ParentRecyclerViewAdapter(
                getParents(40, "")
            )
        }
        searchview = view.findViewById(R.id.searchText) as SearchView

        var noResultsText = view.findViewById(R.id.noResults) as TextView
        var noResultsImage = view.findViewById(R.id.noresultsimage) as ImageView
        searchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "Looking for $query", Toast.LENGTH_LONG).show()

                searchview.clearFocus()
                var categories = getParents(40, query)
                if (categories.isNotEmpty()) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            context,
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
                    searchview.clearFocus()
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            context,
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
        return view


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

        val tv = (R.id.categoryName) as TextView

        //alter text of textview widget
        tv.text = "This text view is clicked"
        //assign the textview forecolor
        tv.setTextColor(Color.BLUE)
    }
}