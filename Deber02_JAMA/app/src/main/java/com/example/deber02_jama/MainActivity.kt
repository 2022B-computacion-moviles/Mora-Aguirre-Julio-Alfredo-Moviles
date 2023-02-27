package com.example.deber02_jama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02_jama.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnClickListener{

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }



    }

    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val xavier = User(1, "Xavier", "Lopez", "https://www.nicepng.com/png/detail/225-2255668_icono-iconos-de-personas-solas.png")
        val julio = User(2, "Julio", "Mora", "https://cdn-icons-png.flaticon.com/512/1198/1198293.png")
        val jonathan = User(3, "Jonathan", "Piedra", "https://cdn-icons-png.flaticon.com/512/3577/3577429.png")
        val kevin = User(4, "Kevin", "Vera", "https://cdn-icons-png.flaticon.com/512/1373/1373255.png")
        val erick = User(1, "Erick", "Muso", "https://www.nicepng.com/png/detail/225-2255668_icono-iconos-de-personas-solas.png")
        val nadia = User(2, "Nadia", "Smith", "https://cdn-icons-png.flaticon.com/512/3577/3577429.png")
        val andres = User(3, "Andres", "Santacruz", "https://cdn-icons-png.flaticon.com/512/1198/1198293.png")
        val alejandro = User(4, "Alejandro", "Perez", "https://cdn-icons-png.flaticon.com/512/1373/1373255.png")

        users.add(xavier)
        users.add(julio)
        users.add(jonathan)
        users.add(kevin)
        users.add(erick)
        users.add(nadia)
        users.add(andres)
        users.add(alejandro)
        users.add(xavier)
        users.add(julio)
        users.add(jonathan)
        users.add(kevin)
        users.add(erick)
        users.add(nadia)
        users.add(andres)
        users.add(alejandro)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}" , Toast.LENGTH_SHORT).show()
        when(position){
            1 -> startActivity(Intent(this, Tarea1::class.java ))
            2 -> startActivity(Intent(this, Tarea2::class.java ))
            3 -> startActivity(Intent(this, Tarea3::class.java ))
            4 -> startActivity(Intent(this, Tarea4::class.java ))
            5 -> startActivity(Intent(this, Tarea5::class.java ))
            6 -> startActivity(Intent(this, Tarea6::class.java ))
            7 -> startActivity(Intent(this, Tarea7::class.java ))
            8 -> startActivity(Intent(this, Tarea8::class.java ))
            9 -> startActivity(Intent(this, Tarea1::class.java ))
            10 -> startActivity(Intent(this, Tarea2::class.java ))
            11 -> startActivity(Intent(this, Tarea3::class.java ))
            12 -> startActivity(Intent(this, Tarea4::class.java ))
            13 -> startActivity(Intent(this, Tarea5::class.java ))
            14 -> startActivity(Intent(this, Tarea6::class.java ))
            15 -> startActivity(Intent(this, Tarea7::class.java ))
            16 -> startActivity(Intent(this, Tarea8::class.java ))
        }


    }

}