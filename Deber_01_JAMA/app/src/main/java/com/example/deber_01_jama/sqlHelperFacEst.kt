package com.example.deber_01_jama

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqlHelperFacEst(contexto: Context?) : SQLiteOpenHelper(
    contexto,
    "deber_01_JAMA",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaCelular = """
            CREATE TABLE celular(
            codCelular VARCHAR(5) PRIMARY KEY,
            nombreCelular VARCHAR(50),
            numeroCamaras INTEGER,
            fechaFabricacion VARCHAR(50),
            dobleLinea VARCHAR(5)
            )
        """.trimIndent()

        val scriptCrearTablaAplicacion = """
            CREATE TABLE aplicacion(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombreAplicacion VARCHAR(50),
            descripcion VARCHAR(50),
            valoraciones VARCHAR(5),
            numeroDescargas INTEGER, 
            cod VARCHAR(5)
            )
        """.trimIndent()

        db?.execSQL(scriptCrearTablaCelular)
        db?.execSQL(scriptCrearTablaAplicacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    /*Celular*/
    fun crearCelular(
        codCelular: String,
        nombreCelular: String,
        numeroCamaras: Int,
        fechaFabricacion: String,
        dobleLinea: String
    ): Boolean {


        val basedatosEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("codCelular", codCelular)
        valoresGuardar.put("nombreCelular", nombreCelular)
        valoresGuardar.put("numCamaras", numeroCamaras)
        valoresGuardar.put("fechaFabricacion", fechaFabricacion)
        valoresGuardar.put("dobleLinea", dobleLinea)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "celular",
                null,
                valoresGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun consultarCelulares(): ArrayList<Celular> {
        var celulares = ArrayList<Celular>()
        var celularEncontrado = Celular("", "", 0, "", "")
        val baseDatosLectura = readableDatabase
        val scriptConsultarCelular = "SELECT * FROM celular"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarCelular,
            null
        )

        if (resultadoConsultaLectura != null && resultadoConsultaLectura.count !=0) {
            resultadoConsultaLectura.moveToFirst()
            do {
                //valor a las variables
                val codCelular = resultadoConsultaLectura.getString(0)
                val nombreCelular = resultadoConsultaLectura.getString(1)
                val numeroCamaras = resultadoConsultaLectura.getInt(2)
                val fechaFabricacion = resultadoConsultaLectura.getString(3)
                val dobleLinea = resultadoConsultaLectura.getString(4)

                if (codCelular != null) {
                    celularEncontrado.codCelular = codCelular
                    celularEncontrado.nombreCelular = nombreCelular
                    celularEncontrado.numeroCamaras = numeroCamaras
                    celularEncontrado.fechaFabricacion = fechaFabricacion
                    celularEncontrado.dobleLinea = dobleLinea

                }
                celulares.add(celularEncontrado)
                celularEncontrado = Celular("", "", 0, "", "")

            } while (resultadoConsultaLectura.moveToNext())

        }else{
            celulares = ArrayList<Celular>()
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return celulares
    }

    fun actualizarCelular(
        codCelularActualizar: String,
        nombreCelular: String,
        numeroCamaras: Int,
        fechaFabricacion: String,
        dobleLinea: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreCelular", nombreCelular)
        valoresAActualizar.put("numeroCamaras", numeroCamaras)
        valoresAActualizar.put("fechaFabricacion", fechaFabricacion)
        valoresAActualizar.put("dobleLinea", dobleLinea)

        val resultadoActualizacion = conexionEscritura
            .update(
                "celular",
                valoresAActualizar,  // Valor actualizar
                "codCelular=?",
                arrayOf(
                    codCelularActualizar
                ) // Parametros Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }

    fun eliminarCelular(codCelular: String): Boolean {

        val conexionEscritura = writableDatabase

        val resultadoEliminacion = conexionEscritura
            .delete(
                "celular",
                "codCelular=?",
                arrayOf(
                    codCelular
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }




    /*Aplicacion*/
    fun crearAplicacion(
        nombreAplicacion: String,
        descripcion: String,
        valoraciones: String,
        numeroDescargas: Int,
        cod: String
    ): Boolean {

        val basedatosEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombreAplicacion", nombreAplicacion)
        valoresGuardar.put("descripcion", descripcion)
        valoresGuardar.put("valoraciones", valoraciones)
        valoresGuardar.put("numeroDescargas", numeroDescargas)
        valoresGuardar.put("cod", cod)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "aplicacion",
                null,
                valoresGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun consultarAplicaciones(codF:String): ArrayList<Aplicacion> {
        var aplicaciones = ArrayList<Aplicacion>()
        var aplicacionEncontrada = Aplicacion(0, "", "", "", 0, "")
        val baseDatosLectura = readableDatabase
        val scriptConsultarAplicacion = "SELECT * FROM aplicacion WHERE cod = '${codF}'"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarAplicacion,
            null
        )

        if (resultadoConsultaLectura != null && resultadoConsultaLectura.count!=0) {
            resultadoConsultaLectura.moveToFirst()
            do {
                //valor a variables
                val id = resultadoConsultaLectura.getInt(0)
                val nombreAplicacion = resultadoConsultaLectura.getString(1) // columna indice 0 -> ID
                val descripcion = resultadoConsultaLectura.getString(2) // Columna indice 1 -> NOMBRE
                val valoraciones = resultadoConsultaLectura.getString(3) // Columna indice 2 -> DESCRIPCION
                val numeroDescargas = resultadoConsultaLectura.getInt(4) // Columna indice 1 -> NOMBRE
                val cod = resultadoConsultaLectura.getString(5) // Columna indice 2 -> DESCRIPCION

                if (cod != null) {
                    aplicacionEncontrada.id = id
                    aplicacionEncontrada.nombreAplicacion = nombreAplicacion
                    aplicacionEncontrada.descripcion = descripcion
                    aplicacionEncontrada.valoraciones = valoraciones
                    aplicacionEncontrada.numeroDescargas = numeroDescargas
                    aplicacionEncontrada.cod = cod

                }
                aplicaciones.add(aplicacionEncontrada)
                aplicacionEncontrada = Aplicacion(0, "", "", "", 0, "")

            } while (resultadoConsultaLectura.moveToNext())

        }else{
            aplicaciones=ArrayList<Aplicacion>()
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return aplicaciones
    }

    fun eliminarAplicacion(id: Int): Boolean {

        val conexionEscritura = writableDatabase

        val resultadoEliminacion = conexionEscritura
            .delete(
                "aplicacion",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarAplicacion(
        idActualizar: Int,
        nombreAplicacion: String,
        descripcion: String,
        valoraciones: String,
        numeroDescargas: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreAplicacion", nombreAplicacion)
        valoresAActualizar.put("descripcion", descripcion)
        valoresAActualizar.put("valoraciones", valoraciones)
        valoresAActualizar.put("numeroDescargas", numeroDescargas)

        val resultadoActualizacion = conexionEscritura
            .update(
                "aplicacion",
                valoresAActualizar,  // Valor actualizar
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }
}