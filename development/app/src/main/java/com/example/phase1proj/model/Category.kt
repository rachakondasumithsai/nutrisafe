package com.example.phase1proj.model

import com.google.gson.annotations.SerializedName

class Category(

    @SerializedName("name")
    val name: String,
    @SerializedName("children")
    var children: MutableList<Item>,
    var id: Int
)