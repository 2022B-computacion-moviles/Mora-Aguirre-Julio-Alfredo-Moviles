
class RelacionCelularAplicacion {
    var celular: Celular = Celular()
    var aplicaciones: ArrayList<Aplicacion> = ArrayList()

    constructor(celular: Celular, aplicacion: ArrayList<Aplicacion>) {
        this.celular = celular
        this.aplicaciones = aplicacion
    }

    override fun toString(): String {
        return "$celular, $aplicaciones"
    }


}