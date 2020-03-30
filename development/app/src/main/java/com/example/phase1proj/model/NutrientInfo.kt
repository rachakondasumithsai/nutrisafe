package com.example.phase1proj.model

import com.google.gson.annotations.SerializedName

class NutrientInfo(
    @SerializedName("name")
    val name: String,

    @SerializedName("Energy")
    var Energy: String,

    @SerializedName("Fat")
    var Fat: String,

    @SerializedName("Carbohydrates")
    var Carbohydrates: String,

    @SerializedName("Protein")
    var Protein: String,

    @SerializedName("Fiber")
    var Fiber: String
)