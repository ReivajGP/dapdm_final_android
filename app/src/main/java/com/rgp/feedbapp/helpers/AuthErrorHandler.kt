package com.rgp.feedbapp.helpers

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.rgp.feedbapp.utils.AppConstants

class AuthErrorHandler {

    private val constants = AppConstants
    fun handleError(result: Task<AuthResult>) : String {

        var errorCause = try {
            (result.exception as FirebaseAuthException).errorCode
        } catch(e: Exception) {
            constants.AUTH_MESSAGE_NO_NETWORK
        }

        when(errorCause) {
            constants.AUTH_ERROR_INVALID_EMAIL -> constants.AUTH_MESSAGE_INVALID_EMAIL
            constants.AUTH_ERROR_WRONG_PASSWORD -> constants.AUTH_MESSAGE_WRONG_PASSWORD
            constants.AUTH_ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_PROVIDER -> constants.AUTH_MESSAGE_ACCOUNT_EXISTS_WITH_DIFFERENT_PROVIDER
            constants.AUTH_ERROR_EMAIL_IN_USE -> constants.AUTH_MESSAGE_EMAIL_IN_USE
            constants.AUTH_ERROR_TOKEN_EXPIRED -> constants.AUTH_MESSAGE_TOKEN_EXPIRED
            constants.AUTH_ERROR_USER_NOT_FOUND -> constants.AUTH_MESSAGE_USER_NOT_FOUND
            constants.AUTH_ERROR_WEAK_PASSWORD -> constants.AUTH_MESSAGE_WEAK_PASSWORD
            constants.NO_NETWORK -> constants.AUTH_MESSAGE_NO_NETWORK
        }

        return String()
    }
}