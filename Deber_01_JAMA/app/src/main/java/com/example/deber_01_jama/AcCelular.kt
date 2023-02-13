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

class AcCelular : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                }
            }
        }

    lateinit var adaptador: ArrayAdapter<Celular>
    lateinit var listview: ListView

    var arreglo: ArrayList<Celular> = ArrayList<Celular>()

    var idItemSelecc = 0
    var idItemSeleccLv = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celular)

        //base Celular
        BaseDatosMemoria.TablaFacEst = sqlHelperFacEst(this)
        arreglo = BaseDatosMemoria.TablaFacEst!!.consultarCelulares()

        //Listview
        listview = findViewById<ListView>(R.id.lvCelular)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listview.adapter = adaptador
        adaptador.notifyDataSetChanged()

        /*Crear Celular*/
        val btnCrearCelular = findViewById<Button>(R.id.btnCrearCelular)
        btnCrearCelular
            .setOnClickListener {
                insertarCelular(adaptador)
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


        //llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_celular, menu)
        //Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        idItemSelecc = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.mi_Editar -> {


                var celular = listview.getItemAtPosition(idItemSeleccLv)

                var codCelular = getCodCelular(celular as Celular)
                var nombreCelular = getNombreCelular(celular as Celular)
                var numeroCamaras = getNumeroCamaras(celular as Celular)
                var fechaFabricacion = getFechaFabricacion(celular as Celular)
                var dobleLinea = getDobleLinea(celular as Celular)

                abrirEditarCelularParametros(
                    EditarCelular::class.java,
                    codCelular,
                    nombreCelular,
                    numeroCamaras,
                    fechaFabricacion,
                    dobleLinea
                )

                return true
            }

            R.id.mi_Eliminar -> {
                "${idItemSelecc}"

                var celular = listview.getItemAtPosition(idItemSeleccLv)

                var codCelular = getCodCelular(celular as Celular)

                eliminarCelular(adaptador, codCelular)
                return true
            }

            R.id.mi_Ver -> {
                "${idItemSelecc}"

                var celular= listview.getItemAtPosition(idItemSeleccLv)

                var codCelular = getCodCelular(celular as Celular)
                var nombreCelular = getNombreCelular(celular as Celular)

                abrirVerAplicacionParametros(AcAplicacion::class.java, codCelular, nombreCelular)


                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun insertarCelular(
        adaptador: ArrayAdapter<Celular>

    ) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        //se llama a la actividad crear_celular, el layout
        val dialogLayout = inflater.inflate(R.layout.crear_celular, null)
        val codCelular_crear = dialogLayout.findViewById<EditText>(R.id.codCelular_crear)
        val nombreCelular_crear = dialogLayout.findViewById<EditText>(R.id.nombreCelular_crear)
        val numeroCamaras_crear = dialogLayout.findViewById<EditText>(R.id.numeroCamaras_crear)
        val fechaFabricacion_crear = dialogLayout.findViewById<EditText>(R.id.fechaFabricacion_crear)
        val dobleLinea_crear = dialogLayout.findViewById<EditText>(R.id.dobleLinea_crear)

        with(builder) {
            setTitle("Celular")
            setPositiveButton("OK") { dialog, which ->
                //Por positivo


                if (BaseDatosMemoria.TablaFacEst != null) {
                    BaseDatosMemoria.TablaFacEst!!.crearCelular(
                        codCelular_crear.text.toString(),
                        nombreCelular_crear.text.toString(),
                        numeroCamaras_crear.text.toString().toInt(),
                        fechaFabricacion_crear.text.toString(),
                        dobleLinea_crear.text.toString(),
                    )
                }

                arreglo.clear()
                arreglo.addAll(BaseDatosMemoria.TablaFacEst!!.consultarCelulares())

                adaptador.notifyDataSetChanged()

            }
            setNegativeButton("Cancel") { dialog, which ->
                //Por negativo
            }
            setView(dialogLayout)
            show()
        }
    }


    fun eliminarCelular(
        adaptador: ArrayAdapter<Celular>,
        codCelular: String

    ) {


        if (BaseDatosMemoria.TablaFacEst != null) {
            BaseDatosMemoria.TablaFacEst!!.eliminarCelular(
                codCelular
            )
        }

        arreglo.clear()
        arreglo.addAll(BaseDatosMemoria.TablaFacEst!!.consultarCelulares())

        adaptador.notifyDataSetChanged()
    }



    fun abrirVerAplicacionParametros(
        clase: Class<*>,
        codCelular: String,
        nombreCelular: String,
    ) {
        val intentExplicito = Intent(this, clase)


        intentExplicito.putExtra(
            "verAplicaciones",
            Celular(codCelular, nombreCelular, 0, "", "")
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun abrirEditarCelularParametros(
        clase: Class<*>,
        codCelular: String,
        nombreCelular: String,
        numeroCamaras: Int,
        fechaFabricacion: String,
        dobleLinea: String


    ) {
        val intentExplicito = Intent(this, clase)


        intentExplicito.putExtra(
            "editarCelular",
            Celular(codCelular, nombreCelular, numeroCamaras, fechaFabricacion, dobleLinea)
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun getCodCelular(celular: Celular): String {
        return "" + celular.codCelular
    }

    fun getNombreCelular(celular: Celular): String {
        return "" + celular.nombreCelular
    }

    fun getNumeroCamaras(celular: Celular): Int {
        return celular.numeroCamaras!!.toInt()
    }

    fun getFechaFabricacion(celular: Celular): String {
        return "" + celular.fechaFabricacion
    }

    fun getDobleLinea(celular: Celular): String {
        return "" + celular.dobleLinea
    }
}