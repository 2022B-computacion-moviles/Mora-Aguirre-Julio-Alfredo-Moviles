package com.example.deber_01_jama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditarCelular : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_celular)

        val getCeular = intent.getParcelableExtra<Celular>("editarCelular")

        var tvEditar = findViewById<TextView>(R.id.tv_Editar)
        var etCodCelular = findViewById<TextView>(R.id.et_CodCelular)
        var etNombreCelular = findViewById<TextView>(R.id.et_NombreCelular)
        var etNumeroCamarasCelular = findViewById<TextView>(R.id.et_NumeroCamarasCelular)
        var etFechaFabricacion = findViewById<TextView>(R.id.et_FechaFabricacion)
        var etDobleLinea = findViewById<TextView>(R.id.et_dobleLinea)

        tvEditar.text = getCeular!!.codCelular + " - " + getCeular.nombreCelular
        etCodCelular.text = getCeular!!.codCelular
        etNombreCelular.text = getCeular.nombreCelular
        etNumeroCamarasCelular.text = getCeular!!.numeroCamaras.toString()
        etFechaFabricacion.text = getCeular!!.fechaFabricacion
        etDobleLinea.text = getCeular!!.dobleLinea

        val btnEditarCelular = findViewById<Button>(R.id.btn_Editar)
        btnEditarCelular
            .setOnClickListener {

                BaseDatosMemoria.TablaCelularAplicacion!!.actualizarCelular(
                    etCodCelular.text.toString(),
                    etNombreCelular.text.toString(),
                    etNumeroCamarasCelular.text.toString().toInt(),
                    etFechaFabricacion.text.toString(),
                    etDobleLinea.text.toString()
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