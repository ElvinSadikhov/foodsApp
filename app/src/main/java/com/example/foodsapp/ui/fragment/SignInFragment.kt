package com.example.foodsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.foodsapp.BuildConfig
import com.example.foodsapp.MainActivity
import com.example.foodsapp.R
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        setupFirebase()
        checkForAuthorizedUser()
        binding.btnSignIn.setOnClickListener {
            signInWithGoogle()
        }

        return binding.root
    }

    private fun setupFirebase() {
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null) {
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.e(LogTags.auth, "Api exception: ${e.message}")
            }
        }
    }

    private fun getClient() : GoogleSignInClient {
        val options = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.FIREBASE_AUTH_WEB_CLIENT_ID)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity as MainActivity, options)
    }

    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            Log.d(LogTags.auth, "Google signIn -> ${if(it.isSuccessful) "success" else "fail"}")
            if(it.isSuccessful) {
                navigateToHomeTab()
            }
        }
    }

    private fun checkForAuthorizedUser() {
        if(auth.currentUser != null) {
            Log.d(LogTags.auth, "User is signed in")
            navigateToHomeTab()
        }
    }

    private fun navigateToHomeTab() = view?.findNavController()?.navigate(R.id.signInToHomeTab)

}