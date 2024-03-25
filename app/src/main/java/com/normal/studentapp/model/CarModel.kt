package com.normal.studentapp.model

data class Car(
    val id : Number,
    var name: String,
    var manufacturer: Manufacturer,
    var colors: ArrayList<String>,
    var images: String
)

data class Manufacturer(
    var name: String,
    var country: String
)