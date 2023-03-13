package com.rgp.feedbapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.rgp.feedbapp.R
import com.rgp.feedbapp.activities.MainActivity
import com.rgp.feedbapp.databinding.FragmentProfileBinding
import com.rgp.feedbapp.helpers.AuthenticationHelper
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.utils.AppConstants
import com.rgp.feedbapp.utils.AuthRequest

class ProfileFragment() : Fragment() {

    // Properties
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    // Fragment lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentProfileBinding.inflate(inflater, container, false).let {
            _binding = it
            it.root
        }

    override fun onStart() {
        super.onStart()
        //auth = FirebaseAuth()
        //val currentUser = auth.currentUser
        //Log.d("PROFILE", "HERE WE ARE!")
        //if (currentUser != null) {

        //}
    }
    // Private methods
    private fun setOnClickListeners() {
        // Inicio de sesión
        binding.btLogin.setOnClickListener {
            if (areTextFieldsEntriesValid()) {
                /*
                //loaderHelper.presentLoader()
                (context as MainActivity).authenticationHelper.logIn(binding.etEmail.text.toString(), binding.etPass.text.toString()) { isLoginSuccessful ->
                  //  loaderHelper.hideLoader()
                    if (isLoginSuccessful) {
                        /*
                        // TODO: Pasar a la pantalla de catálogo
                        val intent = Intent(this, LyricsCatalogActivity::class.java).apply {
                            putExtra(Constants.INTENT_USER_ID, binding.etEmail.text.toString())
                        }
                        startActivity(intent)
                        finish()
                        */
                        Log.d("PERFIL", "SESIÓN INICIADA")
                    } else {
                        Log.d("PERFIL", "PROBLEMA AL INICIAR SESIÓN")
                    }
                }

                 */
            }
        }

        // Registro
        binding.btSignIn.setOnClickListener {
            if (areTextFieldsEntriesValid()) { /*
                //loaderHelper.presentLoader()
                (context as MainActivity).authenticationHelper.signIn(binding.etEmail.text.toString(), binding.etPass.text.toString()) { isSignUpSuccessful ->
                    //loaderHelper.hideLoader()
                    if (isSignUpSuccessful) {
                        // TODO: Ver qué hacer aquí 🥲
                        Log.d("PERFIL", "CUENTA CREADA")
                    } else {
                        Log.d("PERFIL", "PROBLEMA AL CREAR CUENTA")
                    }
                }
                */
            }
        }

        // Recuperación de contraseña
        /*binding.btRecoverPass.setOnClickListener {
            (context as MainActivity).authenticationHelper.recoverPassword(binding.etEmail.text.toString())
        }*/
    }

    private fun areTextFieldsEntriesValid(): Boolean {
        if (binding.etEmail.text.isEmpty() || binding.etPass.text.isEmpty()) {
            activity?.let {
                ToastHelper(it.applicationContext).showToast(constants.ON_EMPTY_FIELD_TOAST_MESSAGE)
            }
            return false
        }
        return true
    }
}