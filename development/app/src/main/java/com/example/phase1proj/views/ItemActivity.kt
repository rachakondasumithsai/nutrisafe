package com.example.phase1proj.views

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.phase1proj.R
import com.example.phase1proj.model.NutrientInfo
import com.google.gson.Gson


// This class is used to show the nutrient information for each nutrient item when
// the user clicks on a nutrient image
class ItemActivity : AppCompatActivity() {

    var context: Context = this@ItemActivity
    var gson = Gson()

    // Creates a table layout object
    lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item)

        title = "Nutrient Information"

        // Assign the table layout which is present in the xml
        tableLayout = findViewById(R.id.tableLayout)

        // Get all the required information from the intent
        val itemName = intent.getStringExtra("itemName")
        val itemNutrient = intent.getStringExtra("itemNutrient")

        // Find the image to be shown in the drawable using the string name from the intent
        val resID = context.resources.getIdentifier(
            intent.getStringExtra("itemThumbnail"),
            "drawable",
            context.packageName
        )

        // Get the nutrient information for an item by calling getNutrientInfo method
        var nutrientInfo = getNutrientInfo(itemName)

        // Set the data for the xml views using the intent information
        findViewById<TextView>(R.id.itemName).text = itemName
        findViewById<TextView>(R.id.nutrientWt).text = itemNutrient
        findViewById<ImageView>(R.id.itemImage).setImageResource(resID)

        // Call to fill all the rows of the table layout
        fillTableLayout(nutrientInfo)
    }

    // A private function to get the nutrient information for an item
    private fun getNutrientInfo(itemName: String): NutrientInfo {

        // Reads the nutrientinfo.json contents to a string
        val text = context?.resources?.openRawResource(R.raw.nutrientinfo)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var item = gson.fromJson(text, Array<NutrientInfo>::class.java).toMutableList()

        // Filter the list and get the first item based on the item name passed in the intent
        return item.filter { it.name.toLowerCase().contains(itemName.toLowerCase()) }[0]
    }

    // A private method to assign the rows of the table layout
    private fun fillTableLayout(nutrientInfo: NutrientInfo) {

        findViewById<TextView>(R.id.textRow2Col1).text = "Energy"
        findViewById<TextView>(R.id.textRow2Col2).text = nutrientInfo.Energy

        findViewById<TextView>(R.id.textRow3Col1).text = "Fat"
        findViewById<TextView>(R.id.textRow3Col2).text = nutrientInfo.Fat

        findViewById<TextView>(R.id.textRow4Col1).text = "Carbohydrates"
        findViewById<TextView>(R.id.textRow4Col2).text = nutrientInfo.Carbohydrates

        findViewById<TextView>(R.id.textRow5Col1).text = "Fibre"
        findViewById<TextView>(R.id.textRow5Col2).text = nutrientInfo.Fiber

        findViewById<TextView>(R.id.textRow6Col1).text = "Protein"
        findViewById<TextView>(R.id.textRow6Col2).text = nutrientInfo.Protein

    }
}
