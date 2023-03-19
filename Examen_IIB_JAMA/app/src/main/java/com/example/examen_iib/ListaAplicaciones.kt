package com.example.examen_iib

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class ListaAplicaciones : AppCompatActivity() {

    val db: FirebaseFirestore = Firebase.firestore
    var aplicaciones: ArrayList<Aplicacion> = arrayListOf<Aplicacion>()
    var idItemSeleccionado = 0
    var cellId = -1
    var aplicacionEditar = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_aplicaciones)

        val celularId =intent.getIntExtra("id",-1)
        cellId = celularId

        var nombreCell = ""

        db.collection("celulares")
            .document(cellId.toString())
            .get()
            .addOnSuccessListener{ celular ->
                nombreCell = celular["nombre"].toString()

            }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val botonFecha = findViewById<Button>(R.id.btn_fecha)

        val listView = findViewById<ListView>(R.id.lv_aplicaciones)
        val nombre = findViewById<EditText>(R.id.nombre_aplicacion)
        val precio = findViewById<EditText>(R.id.valoraciones_aplicacion)
        val fecha = findViewById<EditText>(R.id.fecha_lanzamiento_aplicacion)
        val marca = findViewById<EditText>(R.id.descripcion_aplicacion)
        val si = findViewById<RadioButton>(R.id.rb_si)
        val no = findViewById<RadioButton>(R.id.rb_no)
        val celular = findViewById<TextView>(R.id.tv_celular)
        val id = findViewById<EditText>(R.id.id_aplicacion)


        celular.setText(nombreCell)


        botonFecha.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ _: DatePicker, mYear:Int, mMonth:Int, mDay:Int->
                val mes = mMonth+1
                fecha.setText("$mDay/$mes/$mYear")
            },year,month,day)
            dpd.show()
        }

        db.collection("aplicaciones")
            .whereEqualTo("idCelular",cellId)
            .get()
            .addOnSuccessListener{resultado ->
                for (aplicacion in resultado){
                    aplicaciones.add(
                        Aplicacion(
                            aplicacion.id.toInt(),
                            aplicacion["idCelular"].toString().toInt(),
                            aplicacion["nombre"].toString(),
                            aplicacion["precio"].toString().toDouble(),
                            aplicacion["fecha"].toString(),
                            aplicacion["marca"].toString(),
                            aplicacion["nuevo"].toString().toBoolean())
                    )
                }

                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    aplicaciones
                )

                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
            .addOnFailureListener{ _ ->
                Log.i("ERROR","Sin conexion")
            }

        val botonAddAplicacion = findViewById<Button>(R.id.btn_guardar_aplicacion)
        botonAddAplicacion
            .setOnClickListener{
                crearAplicacion(id,celularId,nombre,precio,fecha,marca,si,no)
            }

        val botonCelulares = findViewById<Button>(R.id.btn_celulares)
        botonCelulares
            .setOnClickListener{
                abrirActividad(MainActivity::class.java)
            }
    }

    fun crearAplicacion(
        id: EditText,
        celularId: Int,
        nombre: EditText,
        precio: EditText,
        fecha: EditText,
        marca: EditText,
        si: RadioButton,
        no: RadioButton
    ){

        var nuevo = false
        if(no.isChecked)
            nuevo = false
        else if(si.isChecked)
            nuevo = true

        val dato = hashMapOf(
            "nombre" to nombre.text.toString(),
            "precio" to precio.text.toString().toDouble(),
            "marca" to marca.text.toString(),
            "fecha" to fecha.text.toString(),
            "nuevo" to nuevo,
            "idCelular" to celularId.toString().toInt()
        )

        db.collection("aplicaciones")
            .document(id.text.toString())
            .set(dato)

        abrirActividadConParametos(ListaAplicaciones::class.java,cellId)
    }

    fun editar(
        id:Int
    ){
        val nombre = findViewById<EditText>(R.id.nombre_aplicacion)
        val precio = findViewById<EditText>(R.id.valoraciones_aplicacion)
        val fecha = findViewById<EditText>(R.id.fecha_lanzamiento_aplicacion)
        val marca = findViewById<EditText>(R.id.descripcion_aplicacion)
        val si = findViewById<RadioButton>(R.id.rb_si)
        val no = findViewById<RadioButton>(R.id.rb_no)
        val id_et = findViewById<EditText>(R.id.id_aplicacion)

        db.collection("aplicaciones")
            .document(id.toString())
            .get()
            .addOnSuccessListener { celular ->
                nombre.setText(celular["nombre"].toString())
                precio.setText(celular["precio"].toString())
                marca.setText(celular["marca"].toString())
                fecha.setText(celular["fecha"].toString())
                id_et.setText(celular.id)
                if(celular["nuevo"].toString().toBoolean())
                    si.setChecked(true)
                else
                    no.setChecked(true)
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //se llenan las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_aplicaciones,menu)
        //obtiene el id del array que se selecciono
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = aplicaciones[id].id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_app_editar->{
                "${idItemSeleccionado}"
                editar(idItemSeleccionado)
                Log.i("Seleccionado", idItemSeleccionado.toString())
                return true
            }
            R.id.mi_app_eliminar->{
                abrirDialogo(idItemSeleccionado)
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(id :Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar aplicacion ?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog,
                    which->
                db.collection("aplicaciones")
                    .document(idItemSeleccionado.toString())
                    .delete()
                abrirActividadConParametos(ListaAplicaciones::class.java,cellId)
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    private fun abrirActividad(
        clase: Class<*>,
    ) {
        val i = Intent(this, clase)
        startActivity(i);
    }

    fun abrirActividadConParametos(
        clase: Class<*>,
        id: Int
    ){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("id",id)
        startActivity(intentExplicito);
    }
}