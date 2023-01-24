import java.time.LocalDate

class OperacionesCrud {

    val rel: Relaciones = Relaciones()

    fun SetearDatos(){
        var celular1: Celular = Celular("Xiaomi10Lite",1, LocalDate.parse("2019-10-01"),210.80,true)
        var aplicacion1: Aplicacion = Aplicacion(1,"FaceBook",  4.4, LocalDate.parse("2010-09-28"),true )
        var aplicacion2: Aplicacion = Aplicacion(2,"Instagram",  4.7, LocalDate.parse("2012-01-28"),true )
        var aplicaciones: ArrayList<Aplicacion> = ArrayList()
        aplicaciones.add(aplicacion1)
        aplicaciones.add(aplicacion2)
        rel.crearRelacionCelularAplicacion(celular1, aplicaciones)
    }

    fun crearCelular(){
        var aplicaciones: ArrayList<Aplicacion> = ArrayList()
        var celular: Celular
        var aplicacion: Aplicacion
        var dobleLinea = false
        var restriccionEdad = false
        var seguir = 0

        println("Ingrese el nombre del Celular: ")
        var nombreCelular = readLine()
        println("Ingrese el numero de camaras del celular")
        var numeroCamaras = readLine()!!.toInt()
        println("Ingrese la fecha de Fabricacion del celular: ")
        var fechaFabricacion = LocalDate.parse(readLine())
        println("Ingrese el precio del celular: ")
        var  precioCelular : Double = readLine()!!.toDouble()
        println("Ingrese 0 si el celular tiene doble linea o 1 si no tiene doble lines: ")
        var opcion = readLine()!!.toInt()
        if(opcion === 0){
            dobleLinea = true
        }
        else if(opcion === 1){
            dobleLinea = false
        }
        celular = Celular( nombreCelular, numeroCamaras, fechaFabricacion, precioCelular, dobleLinea)

        /****/
        println("Ingresar aplicaciones registradas en el celular")
        while (seguir === 0 ){
            println("Ingresa el id de la aplicacion: ")
            var idAplicacion = readLine()!!.toInt()
            println("Ingresa el nombre de la aplicacion: ")
            var nombreAplicacion = readLine()
            println("Ingrese las estrellas desde 0.0 a 5.0")
            var numeroEstrellas = readLine()!!.toDouble()
            println("Ingresa la fecha de lanzamiento: ")
            var fechaLanzamiento = LocalDate.parse(readLine())
            println("Ingresa 0 si tiene restriccion de edad o 1 si no tiene restriccion de edad: ")
            var opcion = readLine()!!.toInt()
            if(opcion === 0 ){
                restriccionEdad = true
            }
            else if(opcion === 1){
                restriccionEdad = false
            }
            aplicacion = Aplicacion(idAplicacion, nombreAplicacion, numeroEstrellas, fechaLanzamiento, restriccionEdad)
            aplicaciones.add(aplicacion)
            println("Ingrese 1 para guardar: ")
            seguir = readLine()!!.toInt()
        }
        rel.crearRelacionCelularAplicacion(celular,aplicaciones)
    }
    /**Funcion que muestra las aplicaciones guardadas hasta el momento**/

    fun mostrarTodasAplicaciones(){
        println("Se esta mostrando todas las aplicaciones guardadas")
        println(rel.obtenerTodasLasRelaciones())
    }

    /**Funcion que muestra los celulares segun su ID**/

    fun obtenerCelularPorId(idCelular: Int){
        println("Celulares segun id: " + idCelular + "\n")
        println(rel.encontrarPorIdCelularAplicacion(idCelular))
    }

    /**Funcion que muestra la aplicacion de un celular**/

    fun obtenerAplicacionDeCelular(idCelular: Int, idAplicacion: Int){
        println("El celular con id " + idCelular + " tiene la aplicacion de id "+idAplicacion+":\n")
        println(rel.encontrarAplicacionDeUnCelular(idCelular, idAplicacion))
    }

    /**Funcion que muestra los celulares segun su ID**/

    fun pedirCrearAplicacionesDeCelular(idCelular: Int){
        var seguir = 0
        var restriccionEdad = false
        var aplicacion : Aplicacion
        println("Ingresar los aplicaciones en determinado celular con id de Celular"+idCelular)
        while (seguir === 0 ) {
            println("Escriba el Identificador de la Aplicacion: ")
            var idAplicacion: Int = readLine()!!.toInt()
            println("Escriba el nombre de la Aplicacion: ")
            var nombreAplicacion = readLine()
            println("Ingrese el numero de estrellas de la aplicacion de 0.0 a 5.0: ")
            var numeroEstrellas = readLine()!!.toDouble()
            println("Escriba su fecha de lanzamiento de la aplicacion: ")
            var fechaLanzamiento = LocalDate.parse(readLine())

            println("Ingrese 0 si la aplicacion tiene restriccion de edad o 1 si no tiene restriccion de edad: ")
            var opcion = readLine()!!.toInt()
            if (opcion === 0) {
                restriccionEdad = true
            } else if (opcion === 1) {
                restriccionEdad = false
            }
            aplicacion = Aplicacion(idAplicacion, nombreAplicacion, numeroEstrellas, fechaLanzamiento, restriccionEdad )
            rel.crearAplcacionesEnCelular(idCelular, aplicacion)
            println("Ingrese 1 para guardar: ")
            seguir = readLine()!!.toInt()
        }
    }
    /**Funcion para borrar una aplicacion por su ID**/
    fun borrarAplicacionPorId(id: Int){
        println("La aplicacion con el id "+ id + " Se eliminó")
        rel.borrarPorIdCelular(id)
    }

    /**Funcion para borrar una aplicacion de un celular**/
    fun borrarAplicacionDeUnCelular(id1: Int, id2: Int){
        println("La aplicacion con id "+ id2 + " correspondiente a celular con id "+ id1 + " Se eliminó")
        rel.borrarPorIdCelularAplicacion(id1, id2)
    }

    /**Funcion para actualizar la aplicacion de un Celular**/

    fun actualizaraplicacionDeCelular(
        idCelular: Int,
        idAplicacion: Int){
        var restriccionEdad = false
        var aplicacion : Aplicacion
        println("Ingresar/actuallizar las aplicaciones del celular con ID de celular "+idCelular)

        println("Escriba el Identificador de la Aplicacion: ")
        var idAplicacion: Int = readLine()!!.toInt()
        println("Escriba el nombre de la Aplicacion: ")
        var nombreAplicacion = readLine()
        println("Ingrese el numero de estrellas de la aplicacion de 0.0 a 5.0: ")
        var numeroEstrellas = readLine()!!.toDouble()
        println("Escriba su fecha de lanzamiento de la aplicacion: ")
        var fechaLanzamiento = LocalDate.parse(readLine())

        println("Ingrese 0 si la aplicacion tiene restriccion de edad o 1 si no tiene restriccion de edad: ")
        var opcion = readLine()!!.toInt()
        if (opcion === 0) {
            restriccionEdad = true
        } else if (opcion === 1) {
            restriccionEdad = false
        }

        aplicacion = Aplicacion(idAplicacion, nombreAplicacion, numeroEstrellas, fechaLanzamiento, restriccionEdad )
        rel.actualizarAplicacionCelular(idCelular, idAplicacion, aplicacion)

    }


    /**Actualizacion Completa**/

    fun actualizacionCompletaCelular(idCelular: Int){

        /**celular**/

        var aplicaciones: ArrayList<Aplicacion> = ArrayList()
        var celular : Celular
        var aplicacion : Aplicacion
        var dobleLinea = false
        var restriccionEdad = false
        var seguir = 0

        println("Ingrese el nombre del Celular: ")
        var nombreCelular = readLine()
        println("Ingrese el numero de camaras del celular")
        var numeroCamaras = readLine()!!.toInt()
        println("Ingrese la fecha de Fabricacion del celular: ")
        var fechaFabricacion = LocalDate.parse(readLine())
        println("Ingrese el precio del celular: ")
        var  precioCelular : Double = readLine()!!.toDouble()
        println("Ingrese 0 si el celular tiene doble linea o 1 si no tiene doble lines: ")
        var opcion = readLine()!!.toInt()
        if(opcion === 0){
            dobleLinea = true
        }
        else if(opcion === 1){
            dobleLinea = false
        }
        celular = Celular( nombreCelular, numeroCamaras, fechaFabricacion, precioCelular, dobleLinea)

        /**aplicacion**/

        println("Ingresar las aplicaciones presentes por en el celular")
        while (seguir === 0){
            println("Escriba el Identificador de la Aplicacion: ")
            var idAplicacion: Int = readLine()!!.toInt()
            println("Escriba el nombre de la Aplicacion: ")
            var nombreAplicacion = readLine()
            println("Ingrese el numero de estrellas de la aplicacion de 0.0 a 5.0: ")
            var numeroEstrellas = readLine()!!.toDouble()
            println("Escriba su fecha de lanzamiento de la aplicacion: ")
            var fechaLanzamiento = LocalDate.parse(readLine())

            println("Ingrese 0 si la aplicacion tiene restriccion de edad o 1 si no tiene restriccion de edad: ")
            var opcion = readLine()!!.toInt()
            if (opcion === 0) {
                restriccionEdad = true
            } else if (opcion === 1) {
                restriccionEdad = false
            }

            aplicacion = Aplicacion(idAplicacion, nombreAplicacion, numeroEstrellas, fechaLanzamiento, restriccionEdad )

            aplicaciones.add(aplicacion)

            println("Ingrese 1 para guardar: ")
            seguir = readLine()!!.toInt()
        }
        rel.actualizarDatosID(idCelular,celular,aplicaciones  )
    }
}


