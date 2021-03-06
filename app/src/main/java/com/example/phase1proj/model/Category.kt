package com.example.phase1proj.model

// Indicates that the json data when serialized should map to the class properties
import com.google.gson.annotations.SerializedName

// Stores the category data. like fruits, vegetables and the different items under each category
// Kotlin allows us to define the constructor within the category class by using class name
// with parenthesis as a function
class Category(

    // This is used only when you need to map with a json having a different name as that of the
    // class property name. This class gets data from category.json file.
    // For simplicity, we are fetching from json. It can be extended to storing in a database too.
    @SerializedName("name")
    val name: String,
    @SerializedName("children")
    var children: MutableList<Item>,
    var id: Int
)