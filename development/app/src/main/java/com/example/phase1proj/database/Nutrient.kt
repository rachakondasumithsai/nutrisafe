package com.example.phase1proj.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrient_data_table")
data class Nutrient(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String,

    //Adding the ColumnInfo annotation is not necessary if you want the column name to be the same as the property name
    var thumbnail: String,


    var category: String,


    var description: String
)
