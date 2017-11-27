package com.tlam.miammiamk.models

class Cuisine {
    var title: String = ""
    var genre: String = ""
    var foods: List<Food> = listOf()

    constructor() {}

    constructor(title: String, genre: String, foods: List<Food>) {
        this.title = title
        this.genre = genre
        this.foods = foods
    }
}
