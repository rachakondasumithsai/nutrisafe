package com.example.phase1proj.model

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("name")
    var name: String = ""

    @SerializedName("category")
    var category: String = ""

    @SerializedName("thumbnail")
    var thumbnail: String? = null
        private set

    @SerializedName("nutrient")
    var nutrient: String? = null
        private set

    var id: String

    constructor(
        name: String,
        category: String,
        thumbnail: String?,
        id: String,
        nutrient: String?
    ) {
        this.name = name
        this.category = category
        this.thumbnail = thumbnail
        this.id = id
        this.nutrient = nutrient
    }

}