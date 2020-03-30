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

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.layout)
    }
}