package com.example.deber_01_jama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditarAplicacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_aplicacion)

        val getAplicacion = intent.getParcelableExtra<Aplicacion>("editarAplicacion")

        var tvAplicacion = findViewById<TextView>(R.id.tv_EditarAplicacion)
        var etNombreAplicacion = findViewById<TextView>(R.id.et_NombreAplicacion)
        var etDescripcion = findViewById<TextView>(R.id.et_Descripcion)
        var etValoraciones = findViewById<TextView>(R.id.et_Valoraciones)
        var etNumeroDescargas = findViewById<TextView>(R.id.et_NumeroDescargas)

        tvAplicacion.text = getAplicacion!!.nombreAplicacion + " - " + getAplicacion.descripcion
        etNombreAplicacion.text = getAplicacion!!.nombreAplicacion
        etDescripcion.text = getAplicacion!!.descripcion
        etValoraciones.text = getAplicacion!!.valoraciones
        etNumeroDescargas.text = getAplicacion.numeroDescargas.toString()

        var id = getAplicacion!!.id

        val btnEditarEst = findViewById<Button>(R.id.btn_AceptarAplicaciones)
        btnEditarEst
            .setOnClickListener {

                BaseDatosMemoria.TablaCelularAplicacion!!.actualizarAplicacion(
                    id!!,
                    etNombreAplicacion.text.toString(),
                    etDescripcion.text.toString(),
                    etValoraciones.text.toString(),
                    etNumeroDescargas.text.toString().toInt()
                )
                irActividad(AcCelular::class.java)

            }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}