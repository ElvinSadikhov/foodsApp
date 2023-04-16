package com.example.foodsapp.ui.viewmodel

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.foodsapp.service.AuthService
import com.example.foodsapp.consts.LogTags
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    fun setupFirebase(fragment: Fragment, onSuccess: (() -> Unit)?) = authService.setupFirebase(fragment, onSuccess)

    fun signInWithGoogle(activity: Activity) = authService.signInWithGoogle(activity)

    fun checkForAuthorizedUser(onSuccess: () -> Unit) {
        if(authService.isSignedIn()) {
            Log.d(LogTags.auth, "User is signed in")
            onSuccess()
        }
    }

}
