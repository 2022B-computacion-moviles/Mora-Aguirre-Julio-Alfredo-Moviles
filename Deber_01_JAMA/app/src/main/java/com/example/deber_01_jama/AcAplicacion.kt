package com.example.deber_01_jama

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class AcAplicacion : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                }
            }
        }


    lateinit var adaptador: ArrayAdapter<Aplicacion>
    lateinit var listview: ListView


    var arreglo: ArrayList<Aplicacion> = ArrayList<Aplicacion>()

    var idItemSelecc = 0
    var idItemSeleccLv = 0

    var codFacEst:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplicacion)

        val verAplicacion = intent.getParcelableExtra   <Celular>("verAplicaciones")
        var tvAplicacion = findViewById<TextView>(R.id.tvAplicacion)

        //referencia al codCelular no al codAplicacion

        codFacEst = verAplicacion!!.codCelular!!

        tvAplicacion.text = verAplicacion!!.codCelular + " - " + verAplicacion!!.nombreCelular



        arreglo = BaseDatosMemoria.TablaCelularAplicacion!!.consultarAplicaciones(verAplicacion!!.codCelular!!)



        //Listview
        listview = findViewById<ListView>(R.id.lvAplicacion)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listview.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearAplicacion = findViewById<Button>(R.id.btnCrearAplicacion)
        btnCrearAplicacion
            .setOnClickListener {
                //referemcoa al cod Celular no cod Aplicacion
                insertarAplicacion(adaptador, verAplicacion!!.codCelular!!)
            }

        listview.setOnItemLongClickListener { parent, view, position, id ->
            idItemSeleccLv = position
            registerForContextMenu(listview)
            openContextMenu(listview)

            true
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)


        //MENU
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_aplicacion, menu)
        //id ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        idItemSelecc = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.mi_EditarAplicacion -> {


                var aplicacion = listview.getItemAtPosition(idItemSeleccLv)

                var id = getIDE(aplicacion as Aplicacion)
                var nombreAplicacion = getNombreAplicacion(aplicacion as Aplicacion)
                var descripcion = getDescripcion(aplicacion as Aplicacion)
                var valoraciones = getValoraciones(aplicacion as Aplicacion)
                var numeroDescargas = getNumeroDescargas(aplicacion as Aplicacion)
                var cod = getCodE(aplicacion as Aplicacion)

                abrirEditarAplicacionParametros(
                    EditarAplicacion::class.java,
                    id,
                    nombreAplicacion,
                    descripcion,
                    valoraciones,
                    numeroDescargas,
                    cod
                )

                return true
            }

            R.id.mi_EliminarAplicacion -> {
                "${idItemSelecc}"


                var aplicacion = listview.getItemAtPosition(idItemSeleccLv)

                var idAplicacion = getIDE(aplicacion as Aplicacion)

                eliminarAplicacion(adaptador, idAplicacion, codFacEst)

                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun insertarAplicacion(
        adaptador: ArrayAdapter<Aplicacion>,
        codi: String

    ) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.crear_aplicacion, null)
        val cod = codi
        val nombreAplicacion_crear = dialogLayout.findViewById<EditText>(R.id.nombreAplicacion_crear)
        val descripcion_crear = dialogLayout.findViewById<EditText>(R.id.descripcion_crear)
        val valoraciones_crear = dialogLayout.findViewById<EditText>(R.id.valoraciones_crear)
        val numeroDescargas_crear = dialogLayout.findViewById<EditText>(R.id.numeroDescargas_crear)

        with(builder) {
            setTitle("Aplicacion")
            setPositiveButton("OK") { dialog, which ->
                //Por positivo

                if (BaseDatosMemoria.TablaCelularAplicacion != null) {
                    BaseDatosMemoria.TablaCelularAplicacion!!.crearAplicacion(
                        nombreAplicacion_crear.text.toString(),
                        descripcion_crear.text.toString(),
                        valoraciones_crear.text.toString(),
                        numeroDescargas_crear.text.toString().toInt(),
                        cod

                    )
                }
                arreglo.clear()
                arreglo.addAll(BaseDatosMemoria.TablaCelularAplicacion!!.consultarAplicaciones(codi))
                adaptador.notifyDataSetChanged()
            }
            setNegativeButton("Cancel") { dialog, which ->
                //caso contrario
            }

            setView(dialogLayout)
            show()
        }
    }

    fun abrirEditarAplicacionParametros(
        clase: Class<*>,
        id: Int,
        nombreAplicacion: String,
        descripcion: String,
        valoraciones: String,
        numeroDescargas: Int,
        cod: String


    ) {
        val intentExplicito = Intent(this, clase)


        intentExplicito.putExtra(
            "editarAplicacion",
            Aplicacion(id, nombreAplicacion, descripcion, valoraciones, numeroDescargas, cod)
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }



    fun eliminarAplicacion(
        adaptador: ArrayAdapter<Aplicacion>,
        id: Int,
        cod:String
    ) {


        if (BaseDatosMemoria.TablaCelularAplicacion != null) {
            BaseDatosMemoria.TablaCelularAplicacion!!.eliminarAplicacion(
                id
            )
        }


        arreglo.clear()
        arreglo.addAll(BaseDatosMemoria.TablaCelularAplicacion!!.consultarAplicaciones(cod))
        adaptador.notifyDataSetChanged()

    }

    fun getIDE(aplicacion: Aplicacion): Int {
        return aplicacion.id!!
    }

    fun getNombreAplicacion(aplicacion: Aplicacion): String {
        return aplicacion.nombreAplicacion!!
    }

    fun getDescripcion(aplicacion: Aplicacion): String {
        return aplicacion.descripcion!!
    }

    fun getValoraciones(aplicacion: Aplicacion): String {
        return aplicacion.valoraciones!!
    }

    fun getNumeroDescargas(aplicacion: Aplicacion): Int {
        return aplicacion.numeroDescargas!!
    }

    fun getCodE(aplicacion: Aplicacion): String {
        return aplicacion.cod!!
    }


}