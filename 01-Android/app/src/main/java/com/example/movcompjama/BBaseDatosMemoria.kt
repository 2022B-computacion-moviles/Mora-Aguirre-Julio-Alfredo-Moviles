package com.example.movcompjama

class BBaseDatosMemoria() {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Julio", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Nadia", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Madelyn", "c@c.com")
                )
        }
    }
}