package com.example.examen_iib

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCelular : AppCompatActivity() {

    val db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_celular)
        var id = intent.getIntExtra("id",-1)

        val btnGuardar = findViewById<Button>(R.id.btn_celular_guardar)
        val nombre_celular_et = findViewById<EditText>(R.id.celular_nombre)
        val precio_celular_et = findViewById<EditText>(R.id.celular_precio)
        val stock_celular_et = findViewById<EditText>(R.id.celular_stock)
        val marca_celular_et = findViewById<EditText>(R.id.celular_marca)
        val si = findViewById<RadioButton>(R.id.celular_si)
        val no = findViewById<RadioButton>(R.id.celular_no)
        val id_celular_et = findViewById<EditText>(R.id.celular_id)
        var estadoNuevo = false

        if(id>=0){
            editar(id,nombre_celular_et, precio_celular_et, stock_celular_et, marca_celular_et, si, no,id_celular_et)
        }

        btnGuardar
            .setOnClickListener{
                if(si.isChecked)
                    estadoNuevo = true
                else estadoNuevo = false

                val dato = hashMapOf(
                    "nombre" to nombre_celular_et.text.toString(),
                    "precio" to precio_celular_et.text.toString().toDouble(),
                    "marca" to marca_celular_et.text.toString(),
                    "stock" to stock_celular_et.text.toString().toInt(),
                    "nuevo" to estadoNuevo
                )

                if(id==-1){
                    id = id_celular_et.text.toString().toInt()
                }

                db.collection("celulares")
                    .document(id.toString())
                    .set(dato)

                abrirActividad(MainActivity::class.java)
            }
    }

    fun editar(
        id:Int,
        nombre:EditText,
        precio:EditText,
        stock:EditText,
        marca:EditText,
        si:RadioButton,
        no:RadioButton,
        id_et: EditText
    ){
        db.collection("celulares")
            .document(id.toString())
            .get()
            .addOnSuccessListener { celular ->
                nombre.setText(celular["nombre"].toString())
                precio.setText(celular["precio"].toString())
                marca.setText(celular["marca"].toString())
                stock.setText(celular["stock"].toString())
                id_et.setText(celular.id)
                if(celular["nuevo"].toString().toBoolean())
                    si.setChecked(true)
                else
                    no.setChecked(true)
            }
    }

    private fun abrirActividad(
        clase: Class<*>,
    ) {
        val i = Intent(this, clase)
        startActivity(i);
    }

}