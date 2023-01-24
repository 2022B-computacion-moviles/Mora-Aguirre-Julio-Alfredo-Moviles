import java.io.File

class Relaciones {
    var relacion: MutableList<RelacionCelularAplicacion> = mutableListOf()

    fun crearRelacionCelularAplicacion(
        celular: Celular,
        aplicaciones: ArrayList<Aplicacion>
    ){
        var lib: String
        relacion!!.add(RelacionCelularAplicacion(celular,aplicaciones))
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicaciones: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
    }

    fun crearAplcacionesEnCelular(
        idCelular: Int,
        aplicacion: Aplicacion
    ) {
        relacion.get(idCelular).aplicaciones.add(aplicacion)
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicaciones: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
    }

    fun obtenerTodasLasRelaciones (): String {
        return File("registrosMasAplicaciones.txt").readText()
    }

    fun encontrarPorIdCelularAplicacion(id: Int): String? {
        return id.toString() + "\t" +
                "Celular: " + relacion.get(id).celular +
                "\n\tAplicacion: "+ relacion.get(id).aplicaciones+"\n"
    }

    fun encontrarAplicacionDeUnCelular(idCelular: Int, idAplicacion: Int): String? {
        return "ID:" + idCelular.toString() + "\t" +
                "Celular registrado: " + relacion.get(idCelular).celular +
                "\n\t\tAplicacion Registrada: "+ relacion.get(idCelular).aplicaciones.get(idAplicacion)+"\n"
    }

    fun borrarPorIdCelular(id: Int): String?{
        relacion.removeAt(id)
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicaciones: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
        return "Se ha eliminado el registro: \n"+ File("registrosMasAplicaciones.txt").readText()
    }

    fun borrarPorIdCelularAplicacion(idCelular: Int, idAplicacion: Int): String? {
        relacion.get(idCelular).aplicaciones.removeAt(idAplicacion)
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicacion: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
        return "Se ha eliminado el registro: \n"+ File("registrosMasAplicaciones.txt").readText()
    }

    fun actualizarAplicacionCelular(idCelular: Int, idAplicacion: Int, aplicacion: Aplicacion): String {
        relacion.get(idCelular).aplicaciones.set(idAplicacion, Aplicacion())
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicaciones: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
        return "Se ha actualizado la base de datos: \n"+ File("registrosMasAplicaciones.txt").readText()
    }

    fun actualizarDatosID(
        id: Int,
        celular: Celular,
        aplicaciones: ArrayList<Aplicacion>): String? {
        relacion.set(id, RelacionCelularAplicacion(celular,aplicaciones))
        File("registrosMasAplicaciones.txt").writeText("")
        File("registros.txt").writeText("")
        for(i in 0 until relacion.size){
            File("registrosMasAplicaciones.txt").appendText("Id: " + i.toString() + "\t" +
                    "Celular: " + relacion.get(i).celular +
                    "\n\t\tAplicaciones: " + " --> "+relacion.get(i).aplicaciones+"\n")
            File("registros.txt").appendText(i.toString()+ "\t" + relacion.get(i).toString() + "\n")
        }
        return "Se actualizo el dato de id: "+ id + "\n"+ File("registrosMasAplicaciones.txt").readText()
    }
}