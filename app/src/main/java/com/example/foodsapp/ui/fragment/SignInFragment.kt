package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodsapp.MainActivity
import com.example.foodsapp.databinding.FragmentSignInBinding
import com.example.foodsapp.ui.viewmodel.CartViewModel
import com.example.foodsapp.ui.viewmodel.SignInViewModel
import com.example.foodsapp.util.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val signInViewModel: SignInViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.signInFragment = this
        (activity as MainActivity).hideBottomNavBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInViewModel.setupFirebase(requireParentFragment(), ::proceed)
        signInViewModel.checkForAuthorizedUser(::proceed)
    }

    private fun proceed()  {
        cartViewModel.init()
        (activity as MainActivity).showBottomNavBar()
        Navigation.go(requireView(), SignInFragmentDirections.signInToHomeTab())
    }

    fun onSignInBtnClick() {
        signInViewModel.signInWithGoogle(activity as MainActivity)
    }

}