package com.rgp.feedbapp.utils

import com.google.android.gms.maps.model.LatLng
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConstants {

    // Intents
    const val INTENT_MAIN_TO_TICKET_SUMMARY_TICKET_ID = "TICKET"

    // URLs
    const val CALENDAR_BASE_URL = "https://private-5acf0e-feedbapp.apiary-mock.com"
    const val CALENDAR_ENDPOINT = "/events"
    const val TICKETS_ENDPOINT = "/tickets"
    const val FACEBOOK_URL = "https://www.facebook.com/feedback.cdmx/"
    const val INSTAGRAM_URL = "https://www.instagram.com/feedback_rock/"
    const val YOUTUBE_URL = "https://www.youtube.com/@feedbackmxoldies7833"

    // Ticket status
    const val TICKET_STATUS_INACTIVE = 0
    const val TICKET_STATUS_ACTIVE = 1

    // Map
    const val MAP_STARTING_ANIMATION_ZOOM = 12f
    const val MAP_STARTING_ANIMATION_MILLISECONDS = 4000
    const val DEFAULT_MAP_LATITUDE = 19.445724627294464
    const val DEFAULT_MAP_LONGITUDE = -99.13744228386442

    // Contract
    const val MINIMUM_PERFORMANCE_SETS = 1
    const val MAXIMUM_PERFORMANCE_SETS = 5
    const val DEFAULT_PERFORMANCE_SETS = 2

    // Calendar
    const val JANUARY_NUMBER = 0
    const val FEBRUARY_NUMBER = 1
    const val MARCH_NUMBER = 2
    const val APRIL_NUMBER = 3
    const val MAY_NUMBER = 4
    const val JUNE_NUMBER = 5
    const val JULY_NUMBER = 6
    const val AUGUST_NUMBER = 7
    const val SEPTEMBER_NUMBER = 8
    const val OCTOBER_NUMBER = 9
    const val NOVEMBER_NUMBER = 10
    const val DECEMBER_NUMBER = 11

    const val JANUARY_STRING = "ENE"
    const val FEBRUARY_STRING = "FEB"
    const val MARCH_STRING = "MAR"
    const val APRIL_STRING = "ABR"
    const val MAY_STRING = "MAY"
    const val JUNE_STRING = "JUN"
    const val JULY_STRING = "JUL"
    const val AUGUST_STRING = "AGO"
    const val SEPTEMBER_STRING = "SEP"
    const val OCTOBER_STRING = "OCT"
    const val NOVEMBER_STRING = "NOV"
    const val DECEMBER_STRING = "DIC"

    // Network connection
    const val NO_NETWORK = "NO_NETWORK"
    const val NO_INTERNET_CONNECTION = "Para poder acceder es necesario estar conectado a Internet."

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
    const val AUTH_MESSAGE_USER_NOT_FOUND = "Usuario no registrado"
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
    const val ON_EMPTY_FIELD_TOAST_MESSAGE = "Para iniciar sesión debes ingresar ambos campos, correo electrónico y contraseña."
    const val ON_EMPTY_MAIL_TOAST_MESSAGE = "Para realizar esta operación es necesario ingresar el correo electrónico"
    const val EMPTY_TEXTFIELD_ENTRIES = "Antes de continuar, verifica que los campos se encuentren llenos"
    const val TICKETS_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE = "¡Oops! El servicio de cargado de tickets no está disponible por el momento. Intenta más tarde."
    const val CALENDAR_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE = "¡Oops! El servicio de cargado de eventos del calendario no está disponible por el momento. Intenta más tarde."
    const val UNABLE_LOAD_CONTENT_UNLESS_LOGGED_IN_TOAST_MESSAGE = "Para poder acceder a todo el contenido de este apartado debes tener tu sesión iniciada."

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