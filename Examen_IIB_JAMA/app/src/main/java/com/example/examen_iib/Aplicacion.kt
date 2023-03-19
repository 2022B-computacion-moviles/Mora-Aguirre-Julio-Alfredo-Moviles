package com.example.examen_iib

class Aplicacion(
    var id:Int,
    var idCelular: Int,
    var nombre:String,
    var valoraciones:Double,
    var fechaLanzamiento: String,
    var descripcion:String,
    var nuevo:Boolean

) {
    override fun toString(): String {
        return "Nombre: ${nombre}\n" +
                "Valoraciones: ${valoraciones}\n" +
                "Fecha de fabricación: ${fechaLanzamiento}\n" +
                "Descripcion: ${descripcion}\n" +
                "Nuevo: ${nuevo}\n"+
                "Id: ${id}"
    }
}