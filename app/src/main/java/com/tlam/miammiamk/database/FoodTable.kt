package com.tlam.miammiamk.database

class FoodTable(
    var id : Int,
    var title : String,    
    var description : String,
    var source : String,
    var cuisine : Int
) : DBModel() {
}

