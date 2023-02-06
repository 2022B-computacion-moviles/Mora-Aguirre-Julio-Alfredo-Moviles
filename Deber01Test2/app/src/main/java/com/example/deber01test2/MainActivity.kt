package com.example.deber01test2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deber01test2.R
import com.example.deber01test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    // variable para ayudar a la conexionde la base de datos
    lateinit var celularDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicializar

        celularDBHelper = miSQLiteHelper(this)

        //asignar contenido al evento click del boton guardar
        binding.btnGuardar.setOnClickListener{
            //comprueba que hay datos

            if(binding.etNombreCelular.text.isNotBlank()&&
                    binding.etNumeroCamaras.text.isNotBlank() &&
                    binding.etAnioFabricacionet.text.isNotBlank() &&
                    binding.etPrecioCelularet.text.isNotBlank() &&
                    binding.etDobleLinea.text.isNotBlank()
                    ){

                //una vez que se comprobo que si ingresan datos, entonces
                //se ayuda de la funcion agregarDato
                celularDBHelper.agregarDato(
                    binding.etNombreCelular.text.toString(),
                    binding.etNumeroCamaras.text.toString().toInt(),
                    binding.etAnioFabricacionet.text.toString().toInt(),
                    binding.etPrecioCelularet.text.toString().toDouble(),
                    binding.etDobleLinea.text.toString().toBoolean()
                    )
                binding.etNombreCelular.text.clear()
                binding.etNumeroCamaras.text.clear()
                binding.etAnioFabricacionet.text.clear()
                binding.etPrecioCelularet.text.clear()
                binding.etDobleLinea.text.clear()
                Toast.makeText(this, "Registros Guardados",
                    Toast.LENGTH_SHORT).show()

            }
        //caso contrario
        else{
        Toast.makeText(this, "No se pueden guardar los registros",
        Toast.LENGTH_LONG).show()

        }

            setContentView(R.layout.activity_main)

        }
    }

}
