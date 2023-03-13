package com.rgp.feedbapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConstants {

    // Intents
    const val INTENT_MAIN_TO_TICKET_SUMMARY_TICKET_ID = "TICKET"

    // URLs
    const val CALENDAR_BASE_URL = "https://private-5acf0e-feedbapp.apiary-mock.com"
    const val CALENDAR_ENDPOINT = "/events"
    const val TICKETS_ENDPOINT = "/tickets"

    // Toasts
    const val ON_EMPTY_FIELD_TOAST_MESSAGE = "Para iniciar sesión debes ingresar ambos campos, correo electrónico y contraseña."

    // Map
    const val MAP_STARTING_ANIMATION_ZOOM = 12f
    const val MAP_STARTING_ANIMATION_MILLISECONDS = 4000

    // Network
    const val NO_NETWORK = "NO_NETWORK"

    // Authentication
    const val AUTH_ERROR_INVALID_EMAIL = "ERROR_INVALID_EMAIL"
    const val AUTH_ERROR_WRONG_PASSWORD = "ERROR_WRONG_PASSWORD"
    const val AUTH_ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_PROVIDER = "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL"
    const val AUTH_ERROR_EMAIL_IN_USE = "ERROR_EMAIL_ALREADY_IN_USE"
    const val AUTH_ERROR_TOKEN_EXPIRED = "ERROR_USER_TOKEN_EXPIRED"
    const val AUTH_ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND"
    const val AUTH_ERROR_WEAK_PASSWORD = "ERROR_WEAK_PASSWORD"

    const val AUTH_MESSAGE_INVALID_EMAIL = "Formato de correo incorrecto"
    const val AUTH_MESSAGE_WRONG_PASSWORD = "Contraseña inválida"
    const val AUTH_MESSAGE_ACCOUNT_EXISTS_WITH_DIFFERENT_PROVIDER = "La cuenta de correo ya ha sido registrada"
    const val AUTH_MESSAGE_EMAIL_IN_USE = "El correo ingresado se encuentra registrado"
    const val AUTH_MESSAGE_TOKEN_EXPIRED = "La sesión ha expirado, intenta ingresar nuevamente"
    const val AUTH_MESSAGE_USER_NOT_FOUND = "Usuario no encontrado"
    const val AUTH_MESSAGE_WEAK_PASSWORD = "La contraseña debe contener al menos 6 caracteres"
    const val AUTH_MESSAGE_NO_NETWORK = "Problema de conexión a Internet"

    const val AUTH_MESSAGE_SUCCESFUL_LOGIN = "Autenticación exitosa"
    const val AUTH_MESSAGE_REGISTER_MAIL_SENT = "Se ha enviado un correo de confirmación"
    const val AUTH_MESSAGE_REGISTER_MAIL_NOT_SENT = "No se logró enviar el correo de confirmación"

    const val AUTH_RESET_PASS_ALERT_DIALOG_TITLE = "Restablecer contraseña"
    const val AUTH_RESET_PASS_ALERT_DIALOG_MESSAGE = "Ingrese su correo para recibir el enlace para restablecer contraseña"
    const val AUTH_RESET_PASS_ALERT_DIALOG_SEND_BUTTON_CAPTION = "Enviar"
    const val AUTH_RESET_PASS_ALERT_DIALOG_CANCEL_BUTTON_CAPTION = "Cancelar"
    const val AUTH_RESET_PASS_ALERT_EMAIL_SENT_MESSAGE = "Se ha enviado un enlace al correo ingresado para restablecer la contraseña"

    // Toast messages
    const val EMPTY_TEXTFIELD_ENTRIES = "Antes de continuar, verifica que los campos se encuentren llenos"

    // Animations constants
    const val NEW_CONTRACT_APPROVED_ANIMATION_SPEED = 0.5F

    // Long texts
    const val NEXT_STEPS_INSTRUCTIONS = """
¡Hemos recibido tu solicitud! 
        
 A partir de ahora tendrás 3 días para realizar la transferencia de apartado para que tu evento quede formalmente agendado. Esto lo podrás hacer accediendo al ticket generado en el apartado de contrataciones.
 Una vez que recibamos la notificación de transferencia, en tu ticket aparecerá el sello de confirmado.
   
¡Agradecemos mucho tu preferencia, nos vemos en el show! 🎸🥁🎤
         
    """
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CALENDAR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}