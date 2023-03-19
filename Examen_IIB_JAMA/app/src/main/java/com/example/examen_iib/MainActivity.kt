package com.example.examen_iib

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db: FirebaseFirestore = Firebase.firestore
    var celulares: ArrayList<Celular> = arrayListOf<Celular>()
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.lv_celulares)

        db.collection("celulares")
            .get()
            .addOnSuccessListener{resultado ->
                for (celular in resultado){
                    celulares.add(
                        Celular(celular.id.toInt(),
                            celular["nombre"].toString(),
                            celular["precio"].toString().toDouble(),
                            celular["stock"].toString().toInt(),
                            celular["marca"].toString(),
                            celular["nuevo"].toString().toBoolean())
                    )
                }

                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    celulares
                )

                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
            .addOnFailureListener{ _ ->
                Log.i("ERROR F","No se ha podido conectar")
            }


        val botonAddCelular = findViewById<Button>(R.id.btn_add_celular)
        botonAddCelular
            .setOnClickListener{
                abrirActividadConParametos(CrearCelular::class.java,-1)
            }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Lenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        //Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = celulares[id].id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar->{
                "${idItemSeleccionado}"
                abrirActividadConParametos(CrearCelular::class.java,idItemSeleccionado)
                Log.i("Seleccionado", idItemSeleccionado.toString())
                return true
            }
            R.id.mi_eliminar->{
                abrirDialogo(idItemSeleccionado)
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_aplicaciones->{
                "${idItemSeleccionado}"
                abrirActividadConParametos(ListaAplicaciones::class.java,idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(id :Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seguro de borrarlo ?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog,
                    which->
                db.collection("celulares")
                    .document(idItemSeleccionado.toString())
                    .delete()
                abrirActividad(MainActivity::class.java)
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun abrirActividadConParametos(
        clase: Class<*>,
        id: Int
    ){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("id",id)
        startActivity(intentExplicito);
    }

    private fun abrirActividad(
        clase: Class<*>,
    ) {
        val i = Intent(this, clase)
        startActivity(i);
    }
}