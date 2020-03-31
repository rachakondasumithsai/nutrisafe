package com.example.phase1proj.views

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
import com.google.gson.Gson

// This is the class that shows the home screen when user logs in to the application
class FragmentHome : Fragment() {

    // Class attributes
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchview: SearchView
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.title = "NutriSafe Home"
        // Contains the fragment view to be shown
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        // Create a recycler view that show the list of categories with items for each categories
        recyclerView = view.findViewById(R.id.recyclerParent) as RecyclerView

        // Add initial data to the recycler view which will call parent recycler view adapter
        // The layout used is linear
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

        // Gets the search view defined in the xml
        searchview = view.findViewById(R.id.searchText) as SearchView

        // Gets the no result text and image defined in the view
        var noResultsText = view.findViewById(R.id.noResults) as TextView
        var noResultsImage = view.findViewById(R.id.noresultsimage) as ImageView

        // Call to set the listener to the search view
        setEventListenerToSearchView(noResultsText, noResultsImage)
        return view

    }

    // A private method which sets the event listener to the search view
    private fun setEventListenerToSearchView(
        noResultsText: TextView,
        noResultsImage: ImageView
    ) {
        searchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            // On submitting the searched word, create a toast and search for that data and
            // add that data to the adapter
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "Looking for $query", Toast.LENGTH_LONG).show()

                searchview.clearFocus()
                var categories = getParents(query)

                // If no data is present, call the no results data
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

            // Do the same whenever there is a change in searched text
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
    }

    // A private function to get the categories of nutrients along with the items under
    // each category using the category.json file
    private fun getParents(searchText: String?): List<Category> {

        // Open the category.json file and read it as a string
        val text = this?.resources?.openRawResource(R.raw.category)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var parents = gson.fromJson(text, Array<Category>::class.java).toMutableList()

        // if there is no search done, do not perform any filtering else filter according to the
        // search text
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

        // Finally return all the categories which has the search text either in category name or
        // in item names under each category
        return parents

    }

}