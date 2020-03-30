package com.example.phase1proj.views

import android.content.Context
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
import com.google.gson.Gson

class FragmentHome : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchview: SearchView
    private val gson = Gson()

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
                getParents("")
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
                var categories = getParents(query)
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
                                getParents("")
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

    private fun getParents(searchText: String?): List<Category> {
        val text = this?.resources?.openRawResource(R.raw.category)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var parents = gson.fromJson(text, Array<Category>::class.java).toMutableList()

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
            }
            parents = parents.filter { it.children.isNotEmpty() }.toMutableList()
        }
        return parents

    }

}