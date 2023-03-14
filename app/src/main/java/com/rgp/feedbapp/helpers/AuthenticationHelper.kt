package com.rgp.feedbapp.helpers

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.rgp.feedbapp.utils.AppConstants

class AuthenticationHelper(private val context: Context) {

    // Properties
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val constants = AppConstants

    // Public Methods
    fun logIn(email: String, pass: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { response ->
            if(response.isSuccessful){
                ToastHelper(context).showToast(AppConstants.AUTH_MESSAGE_SUCCESFUL_LOGIN)
                callback(true)
            }else{
                ToastHelper(context).showToast(AuthErrorHandler().handleError(response))
                callback(false)
            }
        }
    }

    fun signIn(email: String, pass: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { response ->
            if (response.isSuccessful) {
                callback(true)
                firebaseAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                    ToastHelper(context).showToast(constants.AUTH_MESSAGE_REGISTER_MAIL_SENT)

                }?.addOnFailureListener {
                    ToastHelper(context).showToast(constants.AUTH_MESSAGE_REGISTER_MAIL_NOT_SENT)
                }
            } else {
                ToastHelper(context).showToast(AuthErrorHandler().handleError(response))
                callback(false)
            }
        }
    }

    fun recoverPassword(email: String, callback: (Boolean) -> Unit){
        val resetMail = EditText(context)

        resetMail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        val passwordResetDialog = AlertDialog.Builder(context)
        passwordResetDialog.setTitle(constants.AUTH_RESET_PASS_ALERT_DIALOG_TITLE)
        passwordResetDialog.setMessage(constants.AUTH_RESET_PASS_ALERT_DIALOG_MESSAGE)
        passwordResetDialog.setView(resetMail)

        passwordResetDialog.setPositiveButton(constants.AUTH_RESET_PASS_ALERT_DIALOG_SEND_BUTTON_CAPTION, DialogInterface.OnClickListener { dialog, which ->
            val mail = resetMail.text.toString()
            if(mail.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(mail)?.addOnSuccessListener {
                    callback(true)
                    ToastHelper(context).showToast(constants.AUTH_RESET_PASS_ALERT_EMAIL_SENT_MESSAGE)
                }?.addOnFailureListener {
                    callback(false)
                    ToastHelper(context).showToast("${constants.AUTH_MESSAGE_REGISTER_MAIL_NOT_SENT}: ${it.message}")
                }
            }else{
                ToastHelper(context).showToast(constants.AUTH_RESET_PASS_ALERT_DIALOG_MESSAGE)
            }
        })

        passwordResetDialog.setNegativeButton(constants.AUTH_RESET_PASS_ALERT_DIALOG_CANCEL_BUTTON_CAPTION, DialogInterface.OnClickListener { dialog, which ->
        })

        passwordResetDialog.create().show()
    }

    fun signOut(callback: () -> Unit) {
        firebaseAuth.signOut()
        callback()
    }

    fun isUserLoggedIn() : Boolean = firebaseAuth.currentUser != null
}