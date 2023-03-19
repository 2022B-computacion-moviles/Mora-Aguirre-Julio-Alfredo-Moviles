package com.example.snapshots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.snapshots.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 21

    // se llama al binding

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mActiveFragment: Fragment

    private lateinit var mFragmenManager: FragmentManager

    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    private var mFirebaseAuth: FirebaseAuth? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        setupAuth()
        //llamando a la funcion, bottom Nav del home
        setupBottomNav()

    }

    private fun setupAuth() {
        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user == null){
                //el usuario aun no se loggea
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    //.setIsSmartLockEnabled(false)//para que no moleste el dialogo
                    .setAvailableProviders(
                        Arrays.asList(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build())
                    )
                    .build(),RC_SIGN_IN)
            }
        }
    }


    // agregando los 3 fragmentos creados de, perfil, agregar y home
    private fun setupBottomNav(){
        mFragmenManager = supportFragmentManager

        //instanciar los fragmentos disponibles del proyecto

        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val profileFragment = ProfileFragment()
        // se debe saber cual se va a ver primero con fragment, o sea primero va home, add y profile
        mActiveFragment = homeFragment

        //se pasa el primer fragmento, o sea el Home fragment
        mFragmenManager.beginTransaction()
            .add(R.id.hostFragment,profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment).commit()

        mFragmenManager.beginTransaction()
            .add(R.id.hostFragment,addFragment, AddFragment::class.java.name)
            .hide(addFragment).commit()

        mFragmenManager.beginTransaction()
                // no se oculta con el hide porque el sentido es que se vea desde el inicio
            .add(R.id.hostFragment,homeFragment, HomeFragment::class.java.name).commit()
        // para cada uno de los casos, cada que se pulse se ira hacia esa ventana de pulsar, entre ellas home, add, y profile
        //listener select para que se pueda cambiar de pestañas cada que se de click en ese fragmento
        mBinding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    mFragmenManager.beginTransaction().hide(mActiveFragment).show(homeFragment).commit()
                    mActiveFragment = homeFragment
                    true
                }

                R.id.action_add -> {
                    mFragmenManager.beginTransaction().hide(mActiveFragment).show(addFragment).commit()
                    mActiveFragment = addFragment
                    true
                }

                R.id.action_profile -> {
                    mFragmenManager.beginTransaction().hide(mActiveFragment).show(profileFragment).commit()
                    mActiveFragment = profileFragment
                    true
                }
                else -> false
            }

        }

    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth?.addAuthStateListener (mAuthListener)
    }

    override fun onPause() {
        super.onPause()
        mFirebaseAuth?.removeAuthStateListener (mAuthListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "Bienvenido..", Toast.LENGTH_SHORT).show()
            } else{
                if (IdpResponse.fromResultIntent(data) == null){
                    finish()
                }
            }
        }
    }

}