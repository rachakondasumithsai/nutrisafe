package com.example.phase1proj.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import com.example.phase1proj.R
import com.example.phase1proj.model.NutrientInfo
import com.google.gson.Gson

class ItemActivity : AppCompatActivity() {

    var context: Context = this@ItemActivity
    var gson = Gson()
    lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item)

        tableLayout = findViewById(R.id.tableLayout)

        val itemName = intent.getStringExtra("itemName")
        val itemNutrient = intent.getStringExtra("itemNutrient")
        val resID = context.resources.getIdentifier(
            intent.getStringExtra("itemThumbnail"),
            "drawable",
            context.packageName
        )

        var nutrientInfo = getNutrientInfo(itemName)

        findViewById<TextView>(R.id.itemName).text = itemName
        findViewById<TextView>(R.id.nutrientWt).text = itemNutrient
        findViewById<ImageView>(R.id.itemImage).setImageResource(resID)
        fillTableLayout(nutrientInfo)
    }

    private fun getNutrientInfo(itemName: String): NutrientInfo {
        val text = context?.resources?.openRawResource(R.raw.nutrientinfo)
            ?.bufferedReader()
            .use { it?.readText() }

        // Convert the json string to the list using the gson object
        var item = gson.fromJson(text, Array<NutrientInfo>::class.java).toMutableList()

        return item.filter { it.name.toLowerCase().contains(itemName.toLowerCase()) }[0]
    }

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
