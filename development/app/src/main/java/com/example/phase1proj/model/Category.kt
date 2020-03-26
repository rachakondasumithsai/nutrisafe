package com.example.phase1proj.model

import com.example.phase1proj.model.Item

class Category(
    val name: String,
    var children: MutableList<Item>
)