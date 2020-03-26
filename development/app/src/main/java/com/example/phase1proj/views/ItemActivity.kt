package com.example.phase1proj.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.phase1proj.R

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item)
        val itemName = intent.getStringExtra("itemName")
        findViewById<TextView>(R.id.itemDetail).text = itemName

    }
}
