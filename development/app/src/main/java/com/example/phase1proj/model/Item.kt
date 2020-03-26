package com.example.phase1proj.model

class Item {
    var name: String = ""
    var category: String = ""
    var thumbnail: Int? = null
        private set
    var description: String? = null
        private set

    constructor() {}
    constructor(
        name: String,
        category: String,
        thumbnail: Int?,
        description: String?
    ) {
        this.name = name
        this.category = category
        this.thumbnail = thumbnail
        this.description = description
    }

}