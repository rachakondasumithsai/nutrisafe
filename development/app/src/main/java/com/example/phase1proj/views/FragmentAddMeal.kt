package com.example.phase1proj.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.phase1proj.model.NutrientInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_add_meal.*

// Specifies that this kt file is a fragment type class using the : Fragment() statement
// A fragment is a part of activity but not an activity itself
class FragmentAddMeal : Fragment() {

    // Initialize the calories of the meal to 0 and store the positions of the three spinners
    var calories = 0.0
    var pos1 = 0
    var pos2 = 0
    var pos3 = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.title = "Meal Plan"
        // Return the fragment to be viewed in the UI
        return inflater.inflate(com.example.phase1proj.R.layout.fragment_add_meal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Create a gson object which is needed for parsing json data
        var gson = Gson()

        // Read the json file as a string data
        val text = context?.resources?.openRawResource(com.example.phase1proj.R.raw.nutrientinfo)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var itemList = gson.fromJson(text, Array<NutrientInfo>::class.java).toMutableList()

        // Sorts a user-defined object based on a property. By default, it sorts in an
        // ascending order
        itemList.sortBy { it.name }

        // Get a list of a property from the list of objects
        var arrayOfItems = itemList.map { it.name }.toMutableList()

        // Add a default item to the beginning of the list as a placeholder
        arrayOfItems.add(0, "Select a item..")

        // Initialize the views by calling this method
        initializeViews()

        // Set the adapter to the spinner. The adapter contains the data required for the spinner
        // view
        val adapter =
            ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Add data from the array to the adapter
        for (i in 0 until arrayOfItems.size) {
            adapter.add(arrayOfItems[i])
        }

        // Set the adapter for all the spinners
        spinner.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter

        // Call to set event listeners for the spinners
        setListenersForSpinners()


        // Call to set onclick listeners for the buttons
        setEventListenersForButtons(itemList)

    }

    // A private method that sets event listeners for the buttons in the view
    private fun setEventListenersForButtons(itemList: MutableList<NutrientInfo>) {
        button2.setOnClickListener(View.OnClickListener {
            textView6.visibility = View.VISIBLE
            spinner2.visibility = View.VISIBLE
            button3.visibility = View.VISIBLE

        })

        button3.setOnClickListener(View.OnClickListener {
            textView7.visibility = View.VISIBLE
            spinner3.visibility = View.VISIBLE
        })
        button.setOnClickListener(View.OnClickListener {

            // Calculate the total calorie value when user clicks on calculate button
            // for the items selected
            calories = 0.0
            if (pos1 + pos2 + pos3 == 0) {
                Toast.makeText(
                    context,
                    "Please select some item before clicking on calculate",
                    Toast.LENGTH_SHORT
                ).show()
                textView4.text = "0.0"
            } else if (pos1 + pos2 + pos3 > 0) {
                if (pos1 >= 1) {
                    var value = itemList[pos1 - 1].Energy.split(' ')[0]
                    calories += value.toFloat()
                    textView4.text = String.format("%.2f", calories)
                }
                if (pos2 >= 1) {
                    var value = itemList[pos2 - 1].Energy.split(' ')[0]
                    calories += value.toFloat()
                    textView4.text = String.format("%.2f", calories)

                }
                if (pos3 >= 1) {
                    var value = itemList[pos3 - 1].Energy.split(' ')[0]
                    calories += value.toFloat()
                    textView4.text = String.format("%.2f", calories)

                }
            }
        })
    }

    // A private method to set event listeners for the three spinners
    private fun setListenersForSpinners() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No implementation needed
            }

            // When the user selects the item, show a toast message for the item selected
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos1 = spinner.selectedItemPosition
                if (pos1 > 0)
                    Toast.makeText(
                        context,
                        "Selected " + spinner.selectedItem.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

            }

        }
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No implementation needed
            }

            // When the user selects the item, show a toast message for the item selected
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos2 = spinner2.selectedItemPosition
                if (pos2 > 0)
                    Toast.makeText(
                        context,
                        "Selected " + spinner2.selectedItem.toString(),
                        Toast.LENGTH_SHORT
                    ).show()


            }

        }
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No implementation needed
            }

            // When the user selects the item, show a toast message for the item selected
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos3 = spinner3.selectedItemPosition
                if (pos3 > 0)
                    Toast.makeText(
                        context,
                        "Selected " + spinner3.selectedItem.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

            }

        }
    }

    // A private method to initialize the views to default state
    private fun initializeViews() {
        textView6.visibility = View.GONE
        spinner2.visibility = View.GONE
        button3.visibility = View.GONE
        textView7.visibility = View.GONE
        spinner3.visibility = View.GONE
    }

}