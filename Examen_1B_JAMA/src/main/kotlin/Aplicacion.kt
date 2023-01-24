import java.time.LocalDate

class Aplicacion {

    var idAplicacion : Int? = null
    var nombreAplicacion : String ? = null
    var numeroEstrellas : Double ? = null
    var fechaLanzamiento : LocalDate ? = null
    var restriccionEdad : Boolean ? = null


    constructor(
        idAplicacion: Int?,
        nombreAplicacion: String?,
        numeroEstrellas: Double?,
        fechaLanzamiento: LocalDate?,
        restriccionEdad: Boolean?
    ) {
        this.idAplicacion = idAplicacion
        this.nombreAplicacion = nombreAplicacion
        this.numeroEstrellas = numeroEstrellas
        this.fechaLanzamiento = fechaLanzamiento
        this.restriccionEdad = restriccionEdad
    }

    constructor()

    override fun toString(): String {
        return "idAplicacion=$idAplicacion, nombreAplicacion=$nombreAplicacion, numeroEstrellas=$numeroEstrellas, fechaLanzamiento=$fechaLanzamiento, restriccionEdad=$restriccionEdad"
    }


}