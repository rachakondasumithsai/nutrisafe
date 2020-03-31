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

class FragmentAddMeal : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.phase1proj.R.layout.fragment_add_meal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var calories = 0.0
        var pos1 = 0
        var pos2 = 0
        var pos3 = 0

        var gson = Gson()

        val text = context?.resources?.openRawResource(com.example.phase1proj.R.raw.nutrientinfo)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var itemList = gson.fromJson(text, Array<NutrientInfo>::class.java).toMutableList()

        itemList.sortBy { it.name }

        var arrayOfItems = itemList.map { it.name }.toMutableList()

        arrayOfItems.add(0, "Select a item..")


        textView6.visibility = View.INVISIBLE
        spinner2.visibility = View.INVISIBLE
        button3.visibility = View.INVISIBLE
        textView7.visibility = View.INVISIBLE
        spinner3.visibility = View.INVISIBLE


        val adapter =
            ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        for (i in 0 until arrayOfItems.size) {
            adapter.add(arrayOfItems[i])
        }
        spinner.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

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
            }

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
            }

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

            calories = 0.0
            if (pos1 + pos2 + pos3 == 0) {
                Toast.makeText(context, "please select some items", Toast.LENGTH_SHORT).show()
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

}