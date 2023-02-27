package com.example.movcompjama

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
//callback para recibir los datos del intent implicito, llegan los datos
    //se revisa que los datos no sean nulos, con el result ok todo esta bien
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null){
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }
//callback este tambn recibe datos del contenido intent implicito, recibe mas datos
    val contenidoIntentImplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null){
                    if (result.data!!.data != null){
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn", "Telefono ${telefono}")
                    }
                }
            }
        }

//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)
            }

        ///Boton para list view de entrenadores

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener{
                irActividad(BListView::class.java)
            }

        // boton para el intent implicito

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

    //boton intent de actividad con parametros
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParamatros(CIntentExplicitoParametros::class.java)
            }

    }

    //abrir actividad con parametros

    fun abrirActividadConParamatros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parámetros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Julio")
        intentExplicito.putExtra("apellido", "Mora")
        intentExplicito.putExtra("edad", 21)

        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent (this, clase)
        startActivity(intent)
    }
}