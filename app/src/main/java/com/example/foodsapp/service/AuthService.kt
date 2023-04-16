package com.example.foodsapp.service

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.foodsapp.BuildConfig
import com.example.foodsapp.MainActivity
import com.example.foodsapp.consts.LogTags
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService {
    private lateinit var auth: FirebaseAuth
    private lateinit var launcher: ActivityResultLauncher<Intent>

    fun setupFirebase(fragment: Fragment, onSuccess: (() -> Unit)?) {
        auth = Firebase.auth
        launcher = fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            handleActivityResult(it, onSuccess)
        }
    }

    fun signInWithGoogle(activity: Activity) = launcher.launch(getClient(activity).signInIntent)

    fun isSignedIn(): Boolean = auth.currentUser != null

    fun getUserId(): String? = auth.currentUser?.uid

    private fun handleActivityResult(activityResult: ActivityResult, onSuccess: (() -> Unit)?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if(account != null) {
                firebaseAuthWithGoogle(account.idToken!!, onSuccess)
            }
        } catch (e: ApiException) {
            Log.e(LogTags.auth, "Api exception: ${e.message}")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, onSuccess: (() -> Unit)?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            Log.d(LogTags.auth, "Google signIn -> ${if(it.isSuccessful) "success" else "fail"}")
            if(it.isSuccessful && onSuccess != null) {
                onSuccess()
            }
        }
    }

    private fun getClient(activity: Activity) : GoogleSignInClient {
        val options = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.FIREBASE_AUTH_WEB_CLIENT_ID)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity as MainActivity, options)
    }

}