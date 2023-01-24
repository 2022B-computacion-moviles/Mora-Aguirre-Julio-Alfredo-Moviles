import java.time.LocalDate

class Celular {

    /**Constructor Primario**/


    var nombreCelular : String ? = null
    var numeroCamaras : Int? = null
    var fechaFabricacion : LocalDate ? = null
    var precioCelular : Double ? = null
    var dobleLinea : Boolean ? = null


    /**Constructor Secundario**/




    constructor(
        nombreCelular: String?,
        numeroCamaras: Int?,
        fechaFabricacion: LocalDate?,
        precioCelular: Double?,
        dobleLinea: Boolean?
    ) {
        this.nombreCelular = nombreCelular
        this.numeroCamaras = numeroCamaras
        this.fechaFabricacion = fechaFabricacion
        this.precioCelular = precioCelular
        this.dobleLinea = dobleLinea
    }

    constructor()

    override fun toString(): String {
        return "nombreCelular=$nombreCelular, numeroCamaras=$numeroCamaras, fechaFabricacion=$fechaFabricacion, precioCelular=$precioCelular, dobleLinea=$dobleLinea"
    }


}