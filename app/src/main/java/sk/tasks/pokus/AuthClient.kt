package sk.tasks.pokus

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.auth.api.identity.AuthorizationResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.Scope
import com.google.api.services.tasks.TasksScopes

object AuthClient {

    const val REQUEST_AUTH_KEY = 123
    private const val TAG = "AAA"

    /**
     * Launch authorization flow
     */

    fun onAuthStart(activity: Activity) {
        val scopes = listOf(
            Scope(TasksScopes.TASKS),
            Scope(TasksScopes.TASKS_READONLY),
        )

        val authorizationRequest = AuthorizationRequest
            .builder()
            .setRequestedScopes(scopes)
            // ------ WEB client ID - this is accepted (but makes no difference)
            // (with or without this, I still get "Something went wrong" error)
            //.requestOfflineAccess("65098050808-30fj4qjl14dscagut4dpjj1m9h8v979o.apps.googleusercontent.com")

            // ------ANDROID client ID - this is not accepted at all, it immediately throws the error:
            // ERR: 8: [28405], null, 8: [28405], ALL: com.google.android.gms.common.api.ApiException: 8: [28405]
            //.requestOfflineAccess("65098050808-mj3godfph29m78c7in1221db38o6vap8.apps.googleusercontent.com")
            .build()

        Identity
            .getAuthorizationClient(activity)
            .authorize(authorizationRequest)
            .addOnSuccessListener { authResult ->
                if (authResult.hasResolution()) {
                    // Access needs to be granted by the user
                    val pi: PendingIntent = authResult.pendingIntent!!
                    // perhaps I miss some other params in the bundle?
                    try {
                        startIntentSenderForResult(
                            activity,
                            pi.intentSender,
                            REQUEST_AUTH_KEY,
                            null, 0, 0, 0,
                            null
                        )
                    } catch (e: SendIntentException) {
                        Log.e(TAG, "Couldn't start Authorization UI: " + e.localizedMessage)
                    }
                } else {
                    // Access already granted, continue with user action
                    Log.d(TAG, "OK: ${authResult.accessToken}")
                    // TODO: access google tasks api (but we never even get here, so I leave it empty)
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "ERR: $e")
            }
    }

    /**
     * Process the results after the authorization flow is complete
     */

    fun onAuthResult(activity: Activity, data: Intent?) {
        val client = Identity.getAuthorizationClient(activity)

        try {
            val authorizationResult: AuthorizationResult =
                client.getAuthorizationResultFromIntent(data)
            Log.v(
                TAG, "OK: hasResolution = ${authorizationResult.hasResolution()}, " +
                        "grantedScopes = ${authorizationResult.grantedScopes}, " +
                        "serverAuthCode = ${authorizationResult.serverAuthCode}, " +
                        "accessToken = ${authorizationResult.accessToken}"
            )
        } catch (e: Exception) {
            Log.e(TAG, "ERR: ${e.message}, ${e.cause}, ${e.localizedMessage}, ALL: $e")
        }

        // TODO: process later (for now it doesn't work anyway)
    }
}