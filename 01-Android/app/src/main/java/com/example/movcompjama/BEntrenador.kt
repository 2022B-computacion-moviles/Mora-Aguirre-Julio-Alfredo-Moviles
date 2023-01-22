package com.example.movcompjama

class BEntrenador (
    var nombre:String,
    var descripcion:String

    ){

    fun tostring(): String {
        return "{$nombre} - ${descripcion}"
    }

}