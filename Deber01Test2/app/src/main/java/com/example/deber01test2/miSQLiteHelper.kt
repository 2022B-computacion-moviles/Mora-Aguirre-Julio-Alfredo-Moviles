package com.example.deber01test2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper (contex: Context): SQLiteOpenHelper (

    contex,"deber01test2.db", null, 1 ) {

    //CREAR
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCrear = "CREATE TABLE celular"+
                "(idCelular INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombreCelular TEXT, numeroCamaras INTEGER," +
                "anioFabricacion INTEGER, precioCelular DOUBLE," +
                "dobleLinea BOOLEAN)"
        //sentencia para crear la base de datos con la tabla celular
        db!!.execSQL(ordenCrear)
    }


    //ACTUALIZAR añadir mas campos:
    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        val ordenBorrar = "DROP TABLE IF EXISTS celular"
        db!!.execSQL(ordenBorrar)
        onCreate(db)
    }

    //funcion para agregar datos a la base de datos en la tabla celular
    fun agregarDato(nombreCelular: String, numeroCamaras : Int, anioFabricacion: Int, precioCelular: Double, dobleLinea: Boolean) {
        val datos = ContentValues()
        datos.put("nombreCelular", nombreCelular)
        datos.put("numeroCamaras", numeroCamaras)
        datos.put("anioFabricacion", anioFabricacion)
        datos.put("precioCelular", precioCelular)
        datos.put("dobleLinea", dobleLinea)

        //guardar los datos
        val db = this.writableDatabase
        db.insert("celular", null, datos)
        db.close()
    }

}

