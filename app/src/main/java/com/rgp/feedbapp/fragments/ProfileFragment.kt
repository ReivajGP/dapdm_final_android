package com.rgp.feedbapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.FirebaseApp
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
    private lateinit var authenticationHelper: AuthenticationHelper

    // Fragment lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(LayoutInflater.from(context))
        binding.progressBar.visibility = View.INVISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            FirebaseApp.initializeApp(it)
            authenticationHelper = AuthenticationHelper(it)
        }
        setOnClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setOnClickListeners() {
        // Inicio de sesión
        binding.btLogin.setOnClickListener {
            if (areTextFieldsEntriesValid(AuthRequest.LOGIN)) {
                binding.progressBar.visibility = View.VISIBLE
                authenticationHelper.logIn(binding.etEmail.text.toString(), binding.etPass.text.toString()) { isLoginSuccessful ->
                    binding.progressBar.visibility = View.INVISIBLE
                    if (isLoginSuccessful) {
                        binding.avApproved.setAnimation(R.raw.ready)
                        binding.avApproved.playAnimation()
                    } else {
                    }
                }
            } else activity?.let {
                ToastHelper(it.applicationContext).showToast(constants.ON_EMPTY_FIELD_TOAST_MESSAGE)
            }
        }

        // Registro
        binding.btSignIn.setOnClickListener {
            if (areTextFieldsEntriesValid(AuthRequest.SIGNIN)) {
                binding.progressBar.visibility = View.VISIBLE
                authenticationHelper.signIn(binding.etEmail.text.toString(), binding.etPass.text.toString()) { isSignUpSuccessful ->
                    binding.progressBar.visibility = View.INVISIBLE
                    if (isSignUpSuccessful) {
                    } else {
                    }
                }
            } else activity?.let {
                ToastHelper(it.applicationContext).showToast(constants.ON_EMPTY_MAIL_TOAST_MESSAGE)
            }
        }

        // Recuperación de contraseña
        binding.btRecoverPass.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            authenticationHelper.recoverPassword(binding.etEmail.text.toString()) {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun areTextFieldsEntriesValid(request: AuthRequest): Boolean {
        if (binding.etEmail.text.isNotEmpty()) {
            when (request) {
                AuthRequest.LOGIN -> if (binding.etPass.text.isNotEmpty()) return true
                AuthRequest.SIGNIN, AuthRequest.PASS_RECOVERY -> return true
            }
        }
        return false
    }
}